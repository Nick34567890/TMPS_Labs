package client;

public class RadioProtocol implements DeploymentProtocol {
    @Override
    public String getProtocolName() {
        return "Radio Link";
    }

    @Override
    public double getProtocolCost() {
        return 150;
    }
}
