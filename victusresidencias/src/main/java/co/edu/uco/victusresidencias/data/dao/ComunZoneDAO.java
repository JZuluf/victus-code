package co.edu.uco.victusresidencias.data.dao;

import java.util.UUID;
import co.edu.uco.victusresidencias.entity.CommonZoneEntity;

public interface ComunZoneDAO 
    extends RetrieveDAO<CommonZoneEntity, UUID>,
            CreateDAO<CommonZoneEntity>,
            DeleteDAO<UUID>,
            UpdateDAO<CommonZoneEntity> {
}
