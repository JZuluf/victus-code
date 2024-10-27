package co.edu.uco.victusresidencias.data.dao;

import java.util.UUID;
import co.edu.uco.victusresidencias.entity.ComunZoneEntity;

public interface ComunZoneDAO 
    extends RetrieveDAO<ComunZoneEntity, UUID>,
            CreateDAO<ComunZoneEntity>,
            DeleteDAO<UUID>,
            UpdateDAO<ComunZoneEntity> {
}
