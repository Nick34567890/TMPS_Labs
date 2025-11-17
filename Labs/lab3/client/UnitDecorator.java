package client;


public abstract class UnitDecorator implements MilitaryUnit {
    protected final MilitaryUnit unit;

    public UnitDecorator(MilitaryUnit unit) {
        this.unit = unit;
    }

    @Override
    public String getDescription() {
        return unit.getDescription();
    }

    @Override
    public double getBaseCost() {
        return unit.getBaseCost();
    }
}
