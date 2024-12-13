package org.example.socialnetwork.photo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Photo {

    private Long photoId;

    private Long userId;

    private String url;

    private String description;

}
