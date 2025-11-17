package client;


public class HeavyTank implements MilitaryUnit {
    private final DeploymentProtocol protocol;

    public HeavyTank(DeploymentProtocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public String getDescription() {
        return "Heavy Tank (" + protocol.getProtocolName() + ")";
    }

    @Override
    public double getBaseCost() {
        return 9300;
    }

    @Override
    public double getFinalCost() {
        return getBaseCost() + protocol.getProtocolCost();
    }
}
