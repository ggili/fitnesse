package fitnesse.responders.run.formatters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.TimeMeasurement;
import fitnesse.responders.run.CompositeExecutionLog;
import fitnesse.responders.run.TestPage;
import fitnesse.responders.run.TestSummary;
import fitnesse.responders.run.TestSystem;
import fitnesse.wiki.WikiPage;

public class CompositeFormatter extends BaseFormatter {
  List<BaseFormatter> formatters = new ArrayList<BaseFormatter>();

  public void add(BaseFormatter formatter) {
    formatters.add(formatter);
  }

  @Override
  protected WikiPage getPage() {
    throw new RuntimeException("Should not get here.");
  }

  @Override
  public void errorOccured() {
    for (BaseFormatter formatter : formatters)
      formatter.errorOccured();
  }

  @Override
  public void announceNumberTestsToRun(int testsToRun) {
    for (BaseFormatter formatter : formatters)
      formatter.announceNumberTestsToRun(testsToRun);
  }

  @Override
  public void addMessageForBlankHtml() {
    for (BaseFormatter formatter : formatters)
      formatter.addMessageForBlankHtml();
  }

  public void setExecutionLogAndTrackingId(String stopResponderId, CompositeExecutionLog log) {
    for (BaseFormatter formatter : formatters)
      formatter.setExecutionLogAndTrackingId(stopResponderId, log);
  }

  public void testSystemStarted(TestSystem testSystem, String testSystemName, String testRunner) {
    for (BaseFormatter formatter : formatters)
      formatter.testSystemStarted(testSystem, testSystemName, testRunner);
  }

  public void newTestStarted(TestPage test, TimeMeasurement timeMeasurement) throws IOException {
    for (BaseFormatter formatter : formatters)
      formatter.newTestStarted(test, timeMeasurement);
  }

  public void testOutputChunk(String output) throws IOException {
    for (BaseFormatter formatter : formatters)
      formatter.testOutputChunk(output);
  }

  public void testComplete(TestPage test, TestSummary testSummary, TimeMeasurement timeMeasurement) throws IOException {
    for (BaseFormatter formatter : formatters)
      formatter.testComplete(test, testSummary, timeMeasurement);
  }

  @Override
  public void allTestingComplete(TimeMeasurement totalTimeMeasurement) throws IOException {
    for (BaseFormatter formatter : formatters) {
      formatter.allTestingComplete(totalTimeMeasurement);
    }
  }

  public int getErrorCount() {
    int exitCode = 0;
    for (BaseFormatter formatter : formatters)
      exitCode = Math.max(exitCode, formatter.getErrorCount());
    return exitCode;
  }

}
