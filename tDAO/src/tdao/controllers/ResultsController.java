/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao.controllers;

import DTO.TweetDTO;
import java.util.Observer;
import tdao.views.MainForm;

/**
 *
 * @author  Theodore Chrysochoidis
 */
public class ResultsController 
{
    private TweetDTO _model;
    private MainForm _view;   
    public ResultsController(){}
    
    public ResultsController(TweetDTO model, MainForm view) {
        _model = model;
        _view = view;
        _model.addObserver((Observer) view);
    }
    
}// end of ResultsController
