package com.learn.zip;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class AAA implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        println("Start ");
        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {
//                int size = project.getAllprojects().size();
//                for ( :
//                     ) {
//
//                }
                println("");
            }
        });
        
    }
    
    
    private void println(String content){
        System.out.println(content);
    }
}
