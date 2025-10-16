package entity;

import java.util.List;
import java.util.Map;

public record Dictionary(Map<String, List<WordEntry>> data) {
}
