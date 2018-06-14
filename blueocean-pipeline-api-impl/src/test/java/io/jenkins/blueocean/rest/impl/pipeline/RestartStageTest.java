package io.jenkins.blueocean.rest.impl.pipeline;

import hudson.model.Label;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import org.jenkinsci.plugins.workflow.job.WorkflowRun;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class RestartStageTest extends PipelineBaseTest
{
    @Test
    public void restart_stage() throws Exception {
        WorkflowJob p = createWorkflowJobWithJenkinsfile( getClass(), "restartStage.jenkinsfile");
        // Ensure null before first run
        Map pipeline = request().get( String.format( "/organizations/jenkins/pipelines/%s/", p.getName())).build( Map.class);
        Assert.assertNull( pipeline.get( "latestRun"));
        j.createOnlineSlave( Label.get( "first"));

        // Run until completed
        WorkflowRun r = p.scheduleBuild2( 0).waitForStart();
        j.waitForCompletion( r );

        String href = (String)((Map)((Map) ((Map)pipeline.get( "_links" )).get( "runs" ))).get( "href" );

        //Map<String, Object> map = get( href );

        //System.out.println(  );


    }
}
