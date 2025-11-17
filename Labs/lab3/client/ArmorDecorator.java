package client;

public class ArmorDecorator extends UnitDecorator {
    public ArmorDecorator(MilitaryUnit unit) {
        super(unit);
    }

    @Override
    public String getDescription() {
        return unit.getDescription() + " + Reinforced Armor";
    }

    @Override
    public double getFinalCost() {
        return unit.getFinalCost() + 500;
    }
}
