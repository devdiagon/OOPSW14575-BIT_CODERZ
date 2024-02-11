package ec.edu.espe.organivent.controller;

import ec.edu.espe.organivent.model.Schedule;

/**
 *
 * @author Frederick
 */
public class ScheduleController {
    public boolean compareSchedules(Schedule startTime, Schedule endTime){
        boolean passed;
        passed = startTime.getYear()==endTime.getYear();
        if(passed){
            passed = startTime.getMonth()==endTime.getMonth();
            if(passed){
                passed = startTime.getDay()<=endTime.getDay();
                if(passed){
                    passed = startTime.getHours()<endTime.getHours();
                }
            }
        } 
        return passed;
    }
    
}
