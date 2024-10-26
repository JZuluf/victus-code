package co.edu.uco.victusresidencias.data.dao.impl.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;
import co.edu.uco.victusresidencias.crosscutting.exceptions.DataVictusResidenciasException;
import co.edu.uco.victusresidencias.data.dao.ComunZoneDAO;
import co.edu.uco.victusresidencias.data.dao.impl.sql.SqlDAO;
import co.edu.uco.victusresidencias.entity.ComunZoneEntity;

public final class ComunZoneSqlServerDAO extends SqlDAO implements ComunZoneDAO {

    public ComunZoneSqlServerDAO(final Connection connection) {
        super(connection);
    }

	@Override
	public ComunZoneEntity fingByID(UUID id) {
		var comunZoneEntityFilter = new ComunZoneEntity();
        comunZoneEntityFilter.setId(id);

        var result = findByFilter(comunZoneEntityFilter);
        return (result.isEmpty()) ? new ComunZoneEntity() : result.get(0);
	}

    @Override
    public List<ComunZoneEntity> findAll() {
        return findByFilter(new ComunZoneEntity());
    }

    @Override
    public List<ComunZoneEntity> findByFilter(ComunZoneEntity filter) {
        final var statement = new StringBuilder();
        final var parameters = new ArrayList<>();
        final var resultSelect = new ArrayList<ComunZoneEntity>();
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
                var comunZoneEntityTmp = new ComunZoneEntity();

                comunZoneEntityTmp.setId(UUID.fromString(result.getString("id")));
                comunZoneEntityTmp.setName(result.getString("name"));
                comunZoneEntityTmp.setDescripcion(result.getString("description"));

                resultSelect.add(comunZoneEntityTmp);
            }
        } catch (final SQLException exception) {
            var userMessage = "Se ha presentado un problema al realizar la consulta de zonas comunes.";
            var technicalMessage = statementWasPrepared ?
                    "Problema ejecutando la consulta de zonas comunes en la base de datos." :
                    "Problema preparando la consulta de zonas comunes en la base de datos.";

            throw DataVictusResidenciasException.crear(userMessage, technicalMessage, exception);
        }

        return resultSelect;
    }

    @Override
    public void create(ComunZoneEntity data) {
        final StringBuilder statement = new StringBuilder();
        statement.append("INSERT INTO ComunZone(id, name, description) VALUES (?, ?, ?)");

        try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {
            preparedStatement.setObject(1, data.getId());
            preparedStatement.setString(2, data.getName());
            preparedStatement.setString(3, data.getDescripcion());

            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = "Se ha presentado un problema al registrar la nueva zona común.";
            var technicalMessage = "Error al intentar registrar una nueva zona común en la base de datos SQL Server.";

            throw DataVictusResidenciasException.crear(userMessage, technicalMessage, exception);
        }
    }

    @Override
    public void delete(UUID id) {
        final StringBuilder statement = new StringBuilder();
        statement.append("DELETE FROM ComunZone WHERE id = ?");

        try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = "Se ha presentado un problema al eliminar la zona común.";
            var technicalMessage = "Error al intentar eliminar la zona común en la base de datos SQL Server.";

            throw DataVictusResidenciasException.crear(userMessage, technicalMessage, exception);
        }
    }

    @Override
    public void update(ComunZoneEntity data) {
        final StringBuilder statement = new StringBuilder();
        statement.append("UPDATE ComunZone SET name = ?, description = ? WHERE id = ?");

        try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {
            preparedStatement.setString(1, data.getName());
            preparedStatement.setString(2, data.getDescripcion());
            preparedStatement.setObject(3, data.getId());

            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = "Se ha presentado un problema al actualizar la zona común.";
            var technicalMessage = "Error al intentar actualizar la zona común en la base de datos SQL Server.";

            throw DataVictusResidenciasException.crear(userMessage, technicalMessage, exception);
        }
    }

    private void createSelect(final StringBuilder statement) {
        statement.append("SELECT id, name, description ");
    }

    private void createFrom(final StringBuilder statement) {
        statement.append("FROM ComunZone ");
    }

    private void createWhere(final StringBuilder statement, final ComunZoneEntity filter, final List<Object> parameters) {
        if (!UUIDHelper.isDefault(filter.getId())) {
            statement.append("WHERE id = ? ");
            parameters.add(filter.getId());
        }

        if (!TextHelper.isEmpty(filter.getName())) {
            statement.append((parameters.isEmpty()) ? "WHERE " : "AND ");
            statement.append("name = ? ");
            parameters.add(filter.getName());
        }

        if (!TextHelper.isEmpty(filter.getDescripcion())) {
            statement.append((parameters.isEmpty()) ? "WHERE " : "AND ");
            statement.append("description = ? ");
            parameters.add(filter.getDescripcion());
        }
    }

    private void createOrderBy(final StringBuilder statement) {
        statement.append("ORDER BY name ASC");
    }


}
