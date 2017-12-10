package amazinginsidestudios.habit.components;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sangeeth Nandakumar on 10-12-2017.
 */

public class UpdateHabitAcknowledge {

    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("0")
    @Expose
    public List<String> newHabitsSynced = null;
    @SerializedName("1")
    @Expose
    public List<String> updatedHabitsSynced = null;

    public UpdateHabitAcknowledge() {
    }

    public Boolean getStatus() {
        return status;
    }

    public List<String> getNewHabitsSynced() {
        return newHabitsSynced;
    }

    public List<String> getUpdatedHabitsSynced() {
        return updatedHabitsSynced;
    }
}
