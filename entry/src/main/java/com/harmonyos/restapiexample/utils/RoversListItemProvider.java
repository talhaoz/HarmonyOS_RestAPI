package com.harmonyos.restapiexample.utils;

import com.harmonyos.restapiexample.ResourceTable;
import com.harmonyos.restapiexample.network.model.RoverModel;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.*;

import java.util.List;

public class RoversListItemProvider extends BaseItemProvider {

    private final List<RoverModel> roversList;
    private final AbilitySlice slice;

    public RoversListItemProvider(List<RoverModel> roversListP, AbilitySlice slice) {
        this.roversList = roversListP;
        this.slice = slice;
    }

    @Override
    public int getCount() {
        return roversList == null ? 0 : roversList.size();
    }

    @Override
    public Object getItem(int position) {
        if (roversList != null && position >= 0 && position < roversList.size()) {
            return roversList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Component getComponent(int position, Component convertComponent, ComponentContainer componentContainer) {

        final Component cpt;
        if (convertComponent == null) {
            cpt = LayoutScatter.getInstance(slice).parse(ResourceTable.Layout_item_rover, null, false);
        } else {
            cpt = convertComponent;
        }

        RoverModel currentRover = roversList.get(position);
        Text tvRoverName = (Text) cpt.findComponentById(ResourceTable.Id_tvRoverName);
        Text tvRoverLandDate = (Text) cpt.findComponentById(ResourceTable.Id_tvRoverLandDate);

        tvRoverName.setText(currentRover.getName());
        tvRoverLandDate.setText("Landing Date: " + currentRover.getLandingDate());
        return cpt;
    }
}
