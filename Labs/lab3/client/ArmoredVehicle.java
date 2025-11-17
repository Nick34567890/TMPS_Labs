package client;


public class ArmoredVehicle implements MilitaryUnit {
    private final DeploymentProtocol protocol;

    public ArmoredVehicle(DeploymentProtocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public String getDescription() {
        return "Armored Vehicle (" + protocol.getProtocolName() + ")";
    }

    @Override
    public double getBaseCost() {
        return 4700;
    }

    @Override
    public double getFinalCost() {
        return getBaseCost() + protocol.getProtocolCost();
    }
}
