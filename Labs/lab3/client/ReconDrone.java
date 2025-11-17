package client;

public class ReconDrone implements MilitaryUnit {
    private final DeploymentProtocol protocol;

    public ReconDrone(DeploymentProtocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public String getDescription() {
        return "Recon Drone (" + protocol.getProtocolName() + ")";
    }

    @Override
    public double getBaseCost() {
        return 2500;
    }

    @Override
    public double getFinalCost() {
        return getBaseCost() + protocol.getProtocolCost();
    }
}
