package com.harmonyos.restapiexample.slice;

import com.harmonyos.restapiexample.ResourceTable;
import com.harmonyos.restapiexample.network.model.ErrorData;
import com.harmonyos.restapiexample.network.model.NasaRoversModel;
import com.harmonyos.restapiexample.network.model.RoverModel;
import com.harmonyos.restapiexample.utils.RoversListItemProvider;
import com.harmonyos.restapiexample.utils.UiObserver;
import com.harmonyos.restapiexample.viewmodel.NasaRoversViewModel;
import com.harmonyos.restapiexample.viewmodel.NasaRoversViewState;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ListContainer;
import ohos.agp.utils.Color;

import java.util.List;

public class MainAbilitySlice extends AbilitySlice {

    private NasaRoversViewModel nasaRoversViewModel;
    private List<RoverModel> nasaMarsRoversList;
    private ListContainer listContainer;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        bindViews();

        nasaRoversViewModel = new NasaRoversViewModel();
        nasaRoversViewModel.getNasaRovers();
        initObservers();
    }

    private void bindViews() {
        listContainer = (ListContainer) findComponentById(ResourceTable.Id_rovers_list_container);
    }

    private void initObservers() {
        nasaRoversViewModel.getStates().addObserver(new UiObserver<NasaRoversViewState>(this) {
            @Override
            public void onValueChanged(NasaRoversViewState trainsViewState) {

                if (trainsViewState instanceof NasaRoversViewState.Loading) {
                    toggleLoadingDialog(true);
                } else if (trainsViewState instanceof NasaRoversViewState.Error) {
                    toggleLoadingDialog(false);
                    manageError(((NasaRoversViewState.Error) trainsViewState).getMessage());
                } else if (trainsViewState instanceof NasaRoversViewState.NasaRovers) {
                    toggleLoadingDialog(false);
                    NasaRoversModel nasaRoversModel= ((NasaRoversViewState.NasaRovers) trainsViewState).getNasaRoversResponse();
                    if(nasaRoversModel != null)
                        nasaMarsRoversList = nasaRoversModel.getRovers();
                    initListContainer(nasaMarsRoversList);
                }
            }
        }, false);
    }

    private void manageError(ErrorData message) {
    }

    private void toggleLoadingDialog(boolean b) {
    }

    private void initListContainer(List<RoverModel> roversList) {

        if (!roversList.isEmpty()) {
            listContainer.setVisibility(Component.VISIBLE);

            RoversListItemProvider trainListItemProvider = new RoversListItemProvider(roversList, this);
            listContainer.setItemProvider(trainListItemProvider);

            listContainer.enableScrollBar(Component.AXIS_Y, true);
            listContainer.setScrollbarBackgroundColor(Color.GRAY);
            listContainer.setScrollbarColor(Color.WHITE);

            //Enable scrolling with crown button
            listContainer.setFocusable(Component.FOCUS_ADAPTABLE);
            listContainer.requestFocus();

            //Set oval mode
            listContainer.setMode(Component.OVAL_MODE);

            //Adjust the size and position of the scrollbar
            listContainer.setScrollbarStartAngle(-34f);
            listContainer.setScrollbarSweepAngle(70f);

            listContainer.setItemClickedListener((container, component, position, id) -> {

            });
        } else {
            //TODO empty list state
        }
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
