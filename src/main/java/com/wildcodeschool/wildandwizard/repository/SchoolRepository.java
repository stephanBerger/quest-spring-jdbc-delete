package com.wildcodeschool.wildandwizard.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wildcodeschool.wildandwizard.entity.School;

public class SchoolRepository {

	private final static String DB_URL      = "jdbc:mysql://localhost:3306/spring_jdbc_quest?serverTimezone=GMT";
	private final static String DB_USER     = "h4rryp0tt3r";
	private final static String DB_PASSWORD = "Horcrux4life!";

	public void deleteById(Long id) {
		// TODO: delete a school from the database
		try {
			Connection connection = DriverManager.getConnection(
					SchoolRepository.DB_URL, SchoolRepository.DB_USER,
					SchoolRepository.DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(
					"DELETE FROM school WHERE id=?");
			statement.setLong(1, id);

			if (statement.executeUpdate() != 1) {
				throw new SQLException("failed to delete data");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<School> findAll() {

		try {
			Connection connection = DriverManager.getConnection(
					SchoolRepository.DB_URL, SchoolRepository.DB_USER,
					SchoolRepository.DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(
					"SELECT * FROM school;");
			ResultSet resultSet = statement.executeQuery();

			List<School> schools = new ArrayList<>();

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String name = resultSet.getString("name");
				Long capacity = resultSet.getLong("capacity");
				String country = resultSet.getString("country");
				schools.add(new School(id, name, capacity, country));
			}
			return schools;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
