package com.ehsan.negotiation;

import com.ehsan.negotiation.scenario.Scenario;
import com.ehsan.negotiation.scenario.ScenarioOne;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
        Scenario scenario = new ScenarioOne();
        scenario.runScenario();
    }
}
