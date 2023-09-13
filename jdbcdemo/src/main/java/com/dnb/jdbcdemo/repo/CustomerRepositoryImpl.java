//package com.dnb.jdbcdemo.repo;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.dnb.jdbcdemo.dto.Account;
//import com.dnb.jdbcdemo.dto.Customer;
//import com.dnb.jdbcdemo.utils.DBUtils;
//
//@Repository
//public class CustomerRepositoryImpl implements CustomerRepository {
//	
//	@Autowired
//    private DBUtils dbUtils;
//
//	@Override
//	public Customer createAccount(Customer customer) {
//		Optional<Connection> connection = dbUtils.getConnection();
//        String createAccount = "insert into customer"
//                + "(customerId ,customerName ,customerContactNumber ,customerAddress ,customerPAN ,customerUUID)"
//                + " values(?,?,?,?,?,?)";
//
//        PreparedStatement preparedStatement = null;
//
//        if(connection.isPresent()){
//            try {
//                preparedStatement = connection.get().prepareStatement(createAccount);
//
//                preparedStatement.setInt(1,customer.getCustomerId());
//                preparedStatement.setString(2,customer.getCustomerName());
//                preparedStatement.setString(3,customer.getCustomerContactNumber());
//                preparedStatement.setString(4,customer.getCustomerAddress());
//                preparedStatement.setString(5,customer.getCustomerPAN());
//                preparedStatement.setString(6,customer.getCustomerUUID());
//
//                int result = preparedStatement.executeUpdate();
//
//                if(result > 0){
//                    return customer;
//                }
//            }
//            catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//            finally {
//                if(connection.isPresent()){
//                    dbUtils.closeConnection(connection.get());
//                }
//            }
//        }
//		
//		return null;
//	}
//
//	@Override
//	public Optional<Customer> getCustomerById(int customerId) {
//		
//		Optional<Connection> connection = dbUtils.getConnection();
//        PreparedStatement preparedStatement = null;
//        ResultSet resultset = null;
//        String query = "select * from customer where customerId = ?";
//
//        if(connection.isPresent()){
//            try {
//                preparedStatement = connection.get().prepareStatement(query);
//                preparedStatement.setInt(1,customerId);
//
//                resultset = preparedStatement.executeQuery();
//
//                if(resultset.next()){
//                    Customer customer = new Customer();
//                    customer.setCustomerId(resultset.getInt("customerId"));
//                    customer.setCustomerName(resultset.getString("customerName"));
//                    customer.setCustomerContactNumber(resultset.getString("customerContactNumber"));
//                    customer.setCustomerAddress(resultset.getString("customerAddress"));
//                    customer.setCustomerPAN(resultset.getString("customerPAN"));
//                    customer.setCustomerUUID(resultset.getString("customerUUID"));
//
//                    return Optional.of(customer);
//                }
//            }
//            catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//            finally {
//                if(connection.isPresent()){
//                    dbUtils.closeConnection(connection.get());
//                }
//            }
//        }
//		return null;
//	}
//
//	@Override
//	public boolean deleteCustomerById(int customerId) {
//		
//		Optional<Connection> connection = dbUtils.getConnection();
//        PreparedStatement preparedStatement = null;
//
//        String delete = "DELETE FROM customer WHERE customerId = ? ";
//        try {
//
//            preparedStatement = connection.get().prepareStatement(delete);
//            preparedStatement.setInt(1,customerId);
//
//            int result = preparedStatement.executeUpdate();
//
//            if(result > 0){
//                return true;
//            }
//        }
//        catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        finally {
//            connection.ifPresent(dbUtils::closeConnection);
//        }
//		return false;
//	}
//
//	@Override
//	public List<Customer> getAllCustomers() {
//		Optional<Connection> connection = dbUtils.getConnection();
//        ResultSet resultSet = null;
//
//        List<Customer> customers = new ArrayList<>();
//        String getAll = "select * from customer";
//
//        if(connection.isPresent()){
//            try {
//                PreparedStatement preparedStatement = connection.get().prepareStatement(getAll);
//                resultSet = preparedStatement.executeQuery();
//
//                while (resultSet.next()){
//                	Customer customer = new Customer();
//                    customer.setCustomerId(resultSet.getInt("customerId"));
//                    customer.setCustomerName(resultSet.getString("customerName"));
//                    customer.setCustomerContactNumber(resultSet.getString("customerContactNumber"));
//                    customer.setCustomerAddress(resultSet.getString("customerAddress"));
//                    customer.setCustomerPAN(resultSet.getString("customerPAN"));
//                    customer.setCustomerUUID(resultSet.getString("customerUUID"));
//
//                    customers.add(customer);
//                }
//                return customers;
//            }
//            catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//            finally {
//                connection.ifPresent(dbUtils::closeConnection);
//            }
//        }
//		
//		return null;
//	}
//
//}
