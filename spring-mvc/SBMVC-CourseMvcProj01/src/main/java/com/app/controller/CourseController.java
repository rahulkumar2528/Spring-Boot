package com.app.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.entity.Course;
import com.app.service.CourseService;

@Controller
public class CourseController {

	private CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	 @GetMapping("/")
	    public String viewHomePage(Model model) {
	        return findPaginated(1, "courseName", "asc", model);
	    }
	
	@GetMapping("/add")
	public String showNewCourseForm(Model model) {
		Course course = new Course();
		model.addAttribute("course", course);
		return "new_course";
	}

	@PostMapping("/save")
	public String saveCourse(@ModelAttribute("course") Course course) {
		courseService.saveCourse(course);
		return "redirect:/";
	}

	@GetMapping("/update/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		Course course = courseService.getCourseById(id);
		model.addAttribute("course", course);
		return "update_course";
	}

	 @GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                               @RequestParam("sortField") String sortField,
                                               @RequestParam("sortDir") String sortDir,
                                               Model model) {
		int pageSize = 5;
		Page<Course> page = courseService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Course> listCourses = page.getContent();
		
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("currentPage", page.getNumber());
		model.addAttribute("NumberOfElementsOnCurrentPage", page.getNumberOfElements());
		model.addAttribute("totalElements", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listCourses", listCourses);
		return "index";
	}
}
