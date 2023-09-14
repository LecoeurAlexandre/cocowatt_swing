package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatDTO {
    private int drivers;
    private int travellers;
    private double electricPercentage;
    private int doneTrips;
    private int notDoneTrips;
    private int km;
    private int books;
}
