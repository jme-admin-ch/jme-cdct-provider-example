package ch.admin.bit.jeap.jme.cdct.provider.web.api.task;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class TaskCreation {

    @NonNull
    private String title;

    @NonNull
    private String content;

    private String tag;

}
