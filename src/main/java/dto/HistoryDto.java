package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class HistoryDto {
    private int id;
    private String lat;
    private String lnt;
    private String searchDate;

    @Builder
    public HistoryDto(int id, String lat, String lnt, String searchDate) {
        this.id = id;
        this.lat = lat;
        this.lnt = lnt;
        this.searchDate = searchDate;
    }

}
