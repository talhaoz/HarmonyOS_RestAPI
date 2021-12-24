package com.harmonyos.restapiexample.utils;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.abilityjet.activedata.DataObserver;
import ohos.app.dispatcher.TaskDispatcher;

public abstract class UiObserver<T> extends DataObserver<T> {

    private final TaskDispatcher uiTaskDispatcher;

    public UiObserver(AbilitySlice baseAbility) {
        setLifecycle(baseAbility.getLifecycle());
        uiTaskDispatcher = baseAbility.getUITaskDispatcher();
    }

    @Override
    public void onChanged(T t) {
        uiTaskDispatcher.asyncDispatch(() -> onValueChanged(t));
    }

    public abstract void onValueChanged(T t);
}
