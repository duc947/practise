package com.test.manager;

import org.openqa.grid.common.RegistrationRequest;
import org.openqa.grid.internal.utils.SelfRegisteringRemote;
import org.openqa.grid.internal.utils.configuration.GridHubConfiguration;
import org.openqa.grid.internal.utils.configuration.GridNodeConfiguration;
import org.openqa.grid.shared.GridNodeServer;
import org.openqa.grid.web.Hub;
import org.openqa.selenium.remote.server.SeleniumServer;

import com.test.report.ExtentReport;

public class ExecutionManager {
	
	private static final String STARTING_GRID_ERROR = "Problem when strating  grid !!! \n";
	private static final String HUB_CONFIG_JSON =  System.getProperty("user.dir") + "/src/test/resources/common/grid/gridConfig/HubConfig.json";
	private static final String NODE_CONFIG_JSON =  System.getProperty("user.dir") + "/src/test/resources/common/grid/gridConfig/NodeConfig.json";

	private static Hub hub = null;
	private static SelfRegisteringRemote node = null;
	private static ConfigurationManager configuration;
	
	private static ExtentReport report;
	
	public static ExtentReport getReport() {
		return report;
	}

	public static void setReport(ExtentReport report) {
		ExecutionManager.report = report;
	}

	public static void launchGrid() {
		System.out.print("======================[STARTING GRID]====================== \n");
		try {
			hub = new Hub(GridHubConfiguration.loadFromJSON(HUB_CONFIG_JSON));
			hub.start();
			GridNodeConfiguration nodeConfig = GridNodeConfiguration.loadFromJSON(NODE_CONFIG_JSON);
			RegistrationRequest req = new RegistrationRequest(nodeConfig);
			node = new SelfRegisteringRemote(req);
			GridNodeServer server = new SeleniumServer(req.getConfiguration());
			node.setRemoteServer(server);
			node.startRemoteServer();
			node.startRegistrationProcess();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(STARTING_GRID_ERROR);
			e.printStackTrace();
		}
		
	}
	
	public static void stopGrid() {
		System.out.print("======================[STOP GRID]====================== \n");
		node.deleteAllBrowsers();
		node.stopRemoteServer();
	}
	
	public static ConfigurationManager getConfiguration() {
		return configuration;
	}

	public static void setConfiguration(ConfigurationManager configuration) {
		ExecutionManager.configuration = configuration;
	}

}
