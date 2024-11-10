package dao;
import db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import model.Member;
import model.JenisMember;


public class MemberDao{
    public int insert(Member member){
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("insert into member (id,nama,jenis_member_id,) values (?,?,?)");
            statement.setString(1, member.getId());
            statement.setString(2,member.getNama());
            statement.setString(3, member.getJenisMember().getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Member member){
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("update member set nama = ?, jenis_member_id = ? where id = ?");
            statement.setString(1, member.getId());
            statement.setString(2,member.getNama());
            statement.setString(3, member.getJenisMember().getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(Member member){
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("delete from member where id = ?");
            statement.setString(1, member.getId());
            statement.setString(2,member.getNama());
            statement.setString(3, member.getJenisMember().getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<Member> findAll() {
        List<Member> list = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("select member.id, member.nama, jenis_member.id jenis_member_id, jenis_member.nama " + "jenis_member_nama from member join jenis_member on jenis_member.id = member.jenis_member_id");) {
                // Mengambil data
                while (resultSet.next()) {
                    Member Member = new Member();
                    Member.setId(resultSet.getString("id"));
                    Member.setNama(resultSet.getString("nama"));
                    list.add(Member);

                    JenisMember jenisMember = new JenisMember();
                    jenisMember.setId(resultSet.getString("id"));
                    jenisMember.setNama(resultSet.getString("nama"));
                    
                    Member.setJenisMember(jenisMember);

                    list.add(Member);
                }
            } catch (SQLException e) {
                e.getMessage();
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return list;
    }
}
