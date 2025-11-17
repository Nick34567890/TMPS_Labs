package client;

public class SatelliteProtocol implements DeploymentProtocol {
    @Override
    public String getProtocolName() {
        return "Satellite Link";
    }

    @Override
    public double getProtocolCost() {
        return 300;
    }
}
