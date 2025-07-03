package com.example.test.Model;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewSort {
    Long itemId;
    String sort;
}
