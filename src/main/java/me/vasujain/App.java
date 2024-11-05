package me.vasujain;

import java.sql.*;
import java.util.Scanner;

public class App
{
    private static final String URL = "jdbc:mysql://localhost:3306/sampleDB";
    private static final String USER = "root";
    private static final String PASSWORD = "rootpassword";

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO employees (id, name, salary) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            System.out.print("How many employee records do you want to add? ");
            int numberOfRecords = scanner.nextInt();

            for (int i = 1; i <= numberOfRecords; i++) {
                System.out.println("\nEnter details for Employee #" + i);

                System.out.print("Enter ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter Name: ");
                String name = scanner.nextLine();

                System.out.print("Enter Salary: ");
                double salary = scanner.nextDouble();

                pstmt.setInt(1, id);
                pstmt.setString(2, name);
                pstmt.setDouble(3, salary);

                pstmt.executeUpdate();
                System.out.println("Record #" + i + " inserted successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
