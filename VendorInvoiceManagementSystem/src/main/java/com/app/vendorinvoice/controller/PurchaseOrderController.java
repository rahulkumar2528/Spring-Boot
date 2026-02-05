package com.app.vendorinvoice.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.vendorinvoice.constants.Constants;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(Constants.CONT_VENDEOR)
public class PurchaseOrderController {

}
