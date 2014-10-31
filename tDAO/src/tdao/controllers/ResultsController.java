/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao.controllers;

import DTO.TweetDTO;
import java.util.Observer;
import tdao.views.ResultsForm;

/**
 *
 * @author 13
 */
public class ResultsController 
{
    private TweetDTO _model;
    private ResultsForm _view;   
    public ResultsController(){}
    
    public ResultsController(TweetDTO model, ResultsForm view) {
        _model = model;
        _view = view;
        _model.addObserver((Observer) view);
    }
    
}// end of ResultsController
