package com.microfocus.lrc.core.entity

abstract class BaseApiPath(val variables: Map<String, String>) {
    open val path: String = "v1";
    open val httpMethod: String = "GET";
}

class ApiGetLoadTest(variables: Map<String, String>): BaseApiPath(variables) {
    override val path: String = "${super.path}/projects/${this.variables["projectId"]}/load-tests/${this.variables["loadTestId"]}";
}

class ApiStartTestRun(variables: Map<String, String>) : BaseApiPath(variables) {
    override val path: String = "${super.path}/projects/${this.variables["projectId"]}/load-tests/${this.variables["loadTestId"]}/runs";
    override val httpMethod: String = "POST";
}

class ApiGetRunStatus(variables: Map<String, String>) : BaseApiPath(variables) {
    override val path: String = "${super.path}/test-runs/${this.variables["runId"]}/status";
}

class ApiChangeTestRunStatus(variables: Map<String, String>) : BaseApiPath(variables) {
    override val path: String = "${super.path}/test-runs/${this.variables["runId"]}";
    override val httpMethod: String = "PUT";
}

class ApiGetTestRun(variables: Map<String, String>): BaseApiPath(variables) {
    override val path: String = "${super.path}/test-runs/${this.variables["runId"]}";
}

class ApiGenTestRunReport(variables: Map<String, String>): BaseApiPath(variables) {
    override val path: String = "${super.path}/test-runs/${this.variables["runId"]}/reports";
    override val httpMethod: String = "POST";
}

class ApiTestRunReport(variables: Map<String, String>): BaseApiPath(variables) {
    override val path: String = "${super.path}/test-runs/reports/${this.variables["reportId"]}";
    override val httpMethod: String = "GET";
}

class ApiTestRunResults(variables: Map<String, String>): BaseApiPath(variables) {
    override val path: String = "${super.path}/test-runs/${this.variables["runId"]}/results";
    override val httpMethod: String = "GET";
}

class ApiTestRunTx(variables: Map<String, String>): BaseApiPath(variables) {
    override val path: String = "${super.path}/test-runs/${this.variables["runId"]}/transactions";
    override val httpMethod: String = "GET";
}


/**
 * !no api
 *
 */
class ApiDownloadTransactionCSV(variables: Map<String, String>): BaseApiPath(variables) {
    override val path: String = "";
    override val httpMethod: String = "GET";
}

