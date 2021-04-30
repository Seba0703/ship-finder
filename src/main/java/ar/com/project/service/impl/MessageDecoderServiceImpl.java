package ar.com.project.service.impl;

import ar.com.project.dto.MessageDTO;
import ar.com.project.service.MessageDecoderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Service
public class MessageDecoderServiceImpl implements MessageDecoderService {

    private static final String EMPTY = "";
    private static final int NO_GAP = -1;

    public MessageDTO getMessage(List<List<String>> messagesSatellites) {
        int max = this.findMaxMessageLength(messagesSatellites);
        int indexMin = this.findMinLengthIndex(messagesSatellites);

        int gapIndex = 0;
        while(this.existsGap(gapIndex)) {
            gapIndex = this.findGap(messagesSatellites, max);
            if(this.existsGap(gapIndex)) {
                this.removeGap(messagesSatellites, gapIndex, indexMin);
                max--;
            }
        }

        String mssg = this.join(messagesSatellites, max);

        return new MessageDTO(mssg);
    }

    private boolean existsGap(int gapIndex) {
        return gapIndex != NO_GAP;
    }

    private int findMaxMessageLength(List<List<String>> messagesSatellites) {
        return messagesSatellites.stream().max(Comparator.comparingInt(List::size)).get().size();
    }

    /**
     * Return -1 si no encontro un gap, de lo contrario devuelve la posicion del gap
     * */
    private int findGap(List<List<String>> messagesSatellites, int max) {
        int i;
        List<String> previousColumn = new ArrayList<>();
        for(i = 0; i < max; i++) {
            List<String> column = this.buildColumn(messagesSatellites, i);

            boolean empty = this.validateThreeEmpty(column);
            boolean diff = this.validateDiff(column);
            boolean previousSameWord = this.validatePreviousSameWord(column, previousColumn);
            previousColumn = column;

            if(empty || diff || previousSameWord) {
                //Si es diferente el indice con el desfase es el anterior
                if(diff || previousSameWord) {
                    i--;
                }
                break;
            }
        }

        //Si no encontro un gap
        if(i == max) {
            return NO_GAP;
        }

        return i;
    }

    private boolean validatePreviousSameWord(List<String> column, List<String> previousColumn) {
        if(!CollectionUtils.isEmpty(previousColumn)) {
            String previousWord = previousColumn.stream()
                    .filter(StringUtils::isNotEmpty)
                    .distinct()
                    .collect(Collectors.toList())
                    .get(0);
            if(StringUtils.isNotEmpty(previousWord)) {
                return column.stream()
                        .filter(StringUtils::isNotEmpty)
                        .distinct().anyMatch(word -> word.equals(previousWord));
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private String join(List<List<String>> messagesSatellites, int max) {
        StringJoiner joiner = new StringJoiner(" ");

        for(int i = 0; i < max; i++) {
            for (int j = 0; j < messagesSatellites.size(); j++) {
                String word = messagesSatellites.get(j).get(i);
                if(StringUtils.isNotEmpty(word)) {
                    joiner.add(word);
                    break;
                }
            }
        }
        return joiner.toString();
    }

    private int findMinLengthIndex(List<List<String>> messagesSatellites) {
        int min = Integer.MAX_VALUE;
        int minLengthIndex = -1;
        for (int i = 0; i < messagesSatellites.size(); i++) {
            int currentSize = messagesSatellites.get(i).size();
            if(min > currentSize) {
                min = currentSize;
                minLengthIndex = i;
            }
        }

        return minLengthIndex;
    }

    private void removeGap(List<List<String>> messagesSatellites, int i, int indexMin) {
        for (int j = 0; j < messagesSatellites.size(); j++) {
            List<String> msg = messagesSatellites.get(j);
            if (j != indexMin && i < msg.size()) {
                messagesSatellites.get(j).remove(i);
            }
        }

    }

    private boolean validateDiff(List<String> columna) {
        int wordsCount = columna.stream()
                .filter(StringUtils::isNotEmpty)
                .distinct()
                .collect(Collectors.toList()).size();
        return wordsCount > 1;
    }

    private boolean validateThreeEmpty(List<String> columna) {
        return StringUtils.isEmpty(columna.get(0))
                && StringUtils.isEmpty(columna.get(1))
                && StringUtils.isEmpty(columna.get(2));
    }

    private List<String> buildColumn(List<List<String>> messagesSatellites, int i) {
        List<String> column = new ArrayList<>();
        for(int j = 0; j < messagesSatellites.size(); j++) {
            if(i < messagesSatellites.get(j).size()) {
                column.add(messagesSatellites.get(j).get(i));
            } else {
                column.add(EMPTY);
            }
        }

        return column;
    }


}
