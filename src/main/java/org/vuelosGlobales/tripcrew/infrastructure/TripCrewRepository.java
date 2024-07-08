package org.vuelosGlobales.tripcrew.infrastructure;

import org.vuelosGlobales.tripcrew.domain.TripCrew;
import org.vuelosGlobales.tripcrew.domain.TripCrewInfoDTO;

import java.util.List;

public interface TripCrewRepository {
     void save(TripCrew tripCrew);
     List<TripCrewInfoDTO> showCrewByConn(int idConn);
}
