package ch.admin.bit.jeap.jme.cdct.provider.web.api.task;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class Task {

    @NonNull
    private String id;

    @NonNull
    private String title;

    @NonNull
    private String content;

    @NonNull
    private ZonedDateTime createdAt;

    private String tag;

}
