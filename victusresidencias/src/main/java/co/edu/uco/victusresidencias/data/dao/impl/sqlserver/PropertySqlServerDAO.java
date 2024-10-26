package co.edu.uco.victusresidencias.data.dao.impl.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;
import co.edu.uco.victusresidencias.crosscutting.exceptions.DataVictusResidenciasException;
import co.edu.uco.victusresidencias.data.dao.PropertyDAO;
import co.edu.uco.victusresidencias.data.dao.impl.sql.SqlDAO;
import co.edu.uco.victusresidencias.entity.PropertyEntity;
import co.edu.uco.victusresidencias.entity.PropertyZoneEntity;

public final class PropertySqlServerDAO extends SqlDAO implements PropertyDAO {

    public PropertySqlServerDAO(final Connection connection) {
        super(connection);
    }

	@Override
	public PropertyEntity fingByID(UUID id) {
		var propertyEntityFilter = new PropertyEntity();
        propertyEntityFilter.setId(id);

        var result = findByFilter(propertyEntityFilter);
        return (result.isEmpty()) ? new PropertyEntity() : result.get(0);
	}

    @Override
    public List<PropertyEntity> findAll() {
        return findByFilter(new PropertyEntity());
    }

    @Override
    public List<PropertyEntity> findByFilter(PropertyEntity filter) {
        final var statement = new StringBuilder();
        final var parameters = new ArrayList<>();
        final var resultSelect = new ArrayList<PropertyEntity>();
        var statementWasPrepared = false;

        // Select
        createSelect(statement);

        // From
        createFrom(statement);

        // Where
        createWhere(statement, filter, parameters);

        // Order By
        createOrderBy(statement);

        try (var preparedStatement = getConnection().prepareStatement(statement.toString())) {
            for (var arrayIndex = 0; arrayIndex < parameters.size(); arrayIndex++) {
                var statementIndex = arrayIndex + 1;
                preparedStatement.setObject(statementIndex, parameters.get(arrayIndex));
            }

            statementWasPrepared = true;

            final var result = preparedStatement.executeQuery();
            while (result.next()) {
                var propertyEntityTmp = new PropertyEntity();
                var propertyZoneEntityTmp = new PropertyZoneEntity();
                //var residentEntityTmp = new ResidentEntity();

                propertyEntityTmp.setId(UUID.fromString(result.getString("id")));
                propertyEntityTmp.setTipoInmueble(result.getString("name"));//(result.getString("name"));

                propertyZoneEntityTmp.setId(UUID.fromString(result.getString("propertyZone")));
                propertyEntityTmp.setZonaInmueble(propertyZoneEntityTmp);//(propertyZoneEntityTmp);

//                residentEntityTmp.setId(UUID.fromString(result.getString("resident")));
//                propertyEntityTmp.setResident(residentEntityTmp);

                resultSelect.add(propertyEntityTmp);
            }
        } catch (final SQLException exception) {
            var userMessage = "Se ha presentado un problema tratando de realizar la consulta de propiedades.";
            var technicalMessage = statementWasPrepared ?
                    "Problema ejecutando la consulta de propiedades en la base de datos." :
                    "Problema preparando la consulta de propiedades en la base de datos.";

            throw DataVictusResidenciasException.crear(userMessage, technicalMessage, exception);
        }

        return resultSelect;
    }

    @Override
    public void create(PropertyEntity data) {
        final StringBuilder statement = new StringBuilder();
        statement.append("INSERT INTO Property(id, name, propertyZone, resident) VALUES (?, ?, ?, ?)");

        try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {
            preparedStatement.setObject(1, data.getId());
            preparedStatement.setString(2, data.getTipoInmueble());
            preparedStatement.setObject(3, data.getZonaInmueble().getId());
            //preparedStatement.setObject(4, data.getResident().getId());

            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = "Se ha presentado un problema al registrar la nueva propiedad.";
            var technicalMessage = "Error al intentar registrar una nueva propiedad en la base de datos SQL Server.";

            throw DataVictusResidenciasException.crear(userMessage, technicalMessage, exception);
        }
    }

    @Override
    public void delete(UUID id) {
        final StringBuilder statement = new StringBuilder();
        statement.append("DELETE FROM Property WHERE id = ?");

        try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = "Se ha presentado un problema al eliminar la propiedad.";
            var technicalMessage = "Error al intentar eliminar la propiedad en la base de datos SQL Server.";

            throw DataVictusResidenciasException.crear(userMessage, technicalMessage, exception);
        }
    }

    @Override
    public void update(PropertyEntity data) {
        final StringBuilder statement = new StringBuilder();
        statement.append("UPDATE Property SET name = ?, propertyZone = ?, resident = ? WHERE id = ?");

        try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {
            preparedStatement.setString(1, data.getTipoInmueble());
            preparedStatement.setObject(2, data.getZonaInmueble().getId());
            //preparedStatement.setObject(3, data.getResident().getId());
            preparedStatement.setObject(4, data.getId());

            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = "Se ha presentado un problema al actualizar la propiedad.";
            var technicalMessage = "Error al intentar actualizar la propiedad en la base de datos SQL Server.";

            throw DataVictusResidenciasException.crear(userMessage, technicalMessage, exception);
        }
    }

    private void createSelect(final StringBuilder statement) {
        statement.append("SELECT id, name, propertyZone, resident ");
    }

    private void createFrom(final StringBuilder statement) {
        statement.append("FROM Property ");
    }

    private void createWhere(final StringBuilder statement, final PropertyEntity filter, final List<Object> parameters) {
        if (!UUIDHelper.isDefault(filter.getId())) {
            statement.append("WHERE id = ? ");
            parameters.add(filter.getId());
        }

        if (!TextHelper.isEmpty(filter.getTipoInmueble())) {
            statement.append((parameters.isEmpty()) ? "WHERE " : "AND ");
            statement.append("name = ? ");
            parameters.add(filter.getTipoInmueble());
        }

        if (!UUIDHelper.isDefault(filter.getZonaInmueble().getId())) {
            statement.append((parameters.isEmpty()) ? "WHERE " : "AND ");
            statement.append("propertyZone = ? ");
            parameters.add(filter.getZonaInmueble().getId());
        }

//        if (!UUIDHelper.isDefault(filter.getResident().getId())) {
//            statement.append((parameters.isEmpty()) ? "WHERE " : "AND ");
//            statement.append("resident = ? ");
//            parameters.add(filter.getResident().getId());
//        }
    }

    private void createOrderBy(final StringBuilder statement) {
        statement.append("ORDER BY name ASC");
    }


}
