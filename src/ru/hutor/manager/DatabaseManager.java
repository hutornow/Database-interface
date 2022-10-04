package ru.hutor.manager;

import ru.hutor.entity.AgentEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ru.hutor.Application;
public class DatabaseManager {



    public static void insert(AgentEntity entity) throws SQLException {
        try(Connection c = Application.getConnection()){
            String sql = "INSERT INTO `test`.`agent`(`Title`,`AgentType`,`Address`,`Phone`,`Email`,`Logo`,`Priority`) VALUES(?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pr = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pr.setString(1, entity.getTitle());
            pr.setString(2, entity.getAgentType());
            pr.setString(3, entity.getAddress());
            pr.setString(4, entity.getPhone());
            pr.setString(5, entity.getEmail());
            pr.setString(6, entity.getLogoPath());
            pr.setInt(7, entity.getPriority());
            pr.executeUpdate();
            ResultSet resultSet = pr.getGeneratedKeys();
            if(resultSet.next()){
                entity.setId(resultSet.getInt(1));
            }
        }
    }

    public static void update(AgentEntity entity) throws SQLException {
        try(Connection c = Application.getConnection()){
            String sql = "UPDATE `test`.`agent` SET `Title` = ?, `AgentType` = ?, `Address` = ?, `Phone` = ?, `Email` = ?, `Logo` = ?, `Priority` = ? WHERE `ID` = ?;";
            PreparedStatement pr = c.prepareStatement(sql);
            pr.setString(1, entity.getTitle());
            pr.setString(2, entity.getAgentType());
            pr.setString(3, entity.getAddress());
            pr.setString(4, entity.getPhone());
            pr.setString(5, entity.getEmail());
            pr.setString(6, entity.getLogoPath());
            pr.setInt(7, entity.getPriority());
            pr.setInt(8, entity.getId());
            pr.executeUpdate();

        }
    }

    public static List<AgentEntity> selectAll() throws SQLException {{
        try(Connection c = Application.getConnection()){
            String sql = "SELECT * FROM `test`.`agent`;";
            Statement statement = c.createStatement();
            ResultSet agents = statement.executeQuery(sql);
            List<AgentEntity> result = new ArrayList<>();
            while (agents.next()){
                result.add(new AgentEntity(
                        agents.getInt(1),
                        agents.getString(2),
                        agents.getString(3),
                        agents.getString(4),
                        agents.getString(5),
                        agents.getString(6),
                        agents.getString(7),
                        agents.getInt(8)
                ));
            }
            return result;
        }
    }
}

    public static void delete(int id) throws SQLException{
        try(Connection c = Application.getConnection()){
            String sql = "DELETE FROM `test`.`agent` WHERE `ID` = ?;";
            PreparedStatement pr = c.prepareStatement(sql);
            pr.setInt(1, id);
            pr.executeUpdate();
        }
    }



}
