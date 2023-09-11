package com.dnb.jdbcdemo.repo;

import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.dto.Customer;
import com.dnb.jdbcdemo.exceptions.InvalidDateException;
import com.dnb.jdbcdemo.utils.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    @Autowired
    private DBUtils dbUtils;
    @Override
    public Account createAccount(Account account) {

        Optional<Connection> connection = dbUtils.getConnection();
        String createAccount = "insert into account"
                + "(accountId ,accountHolderName ,accountType ,balance ,contactNumber ,address ,accountCreatedDate ,dob, accountStatus, customerId)"
                + " values(?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = null;

        if(connection.isPresent()){
            try {
                preparedStatement = connection.get().prepareStatement(createAccount);

                preparedStatement.setString(1,account.getAccountId());
                preparedStatement.setString(2,account.getAccountHolderName());
                preparedStatement.setString(3,account.getAccountType());
                preparedStatement.setFloat(4,account.getBalance());
                preparedStatement.setString(5,account.getContactNumber());
                preparedStatement.setString(6,account.getAddress());
                preparedStatement.setDate(7, java.sql.Date.valueOf(account.getAccountCreatedDate()));
                preparedStatement.setDate(8, java.sql.Date.valueOf(account.getDob()));
                preparedStatement.setBoolean(9, account.isAccountStatus());
                preparedStatement.setInt(10, account.getCustomer().getCustomerId());

                int result = preparedStatement.executeUpdate();

                if(result > 0){
                    return account;
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
            finally {
                if(connection.isPresent()){
                    dbUtils.closeConnection(connection.get());
                }
            }
        }

        return null;
    }

    @Override
    public Optional<Account> getAccountById(String accountId) throws InvalidNameException, InvalidDateException {

        Optional<Connection> connection = dbUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        String query = "select * from account where accountId = ?";

        if(connection.isPresent()){
            try {
                preparedStatement = connection.get().prepareStatement(query);
                preparedStatement.setString(1,accountId);

                resultset = preparedStatement.executeQuery();

                if(resultset.next()){
                    
                    
                    String accountHolderName = resultset.getString("accountHolderName");
                    String accountType = resultset.getString("accountType");
                    Float accountBalance = resultset.getFloat("balance");
                    String contactNumber = resultset.getString("contactNumber");
                    String address = resultset.getString("address");
                    LocalDate accountCreateDate = resultset.getDate("accountCreatedDate").toLocalDate();
                    LocalDate  dob = resultset.getDate("dob").toLocalDate();
                    Boolean accountStatus = resultset.getBoolean("accountStatus");
             
                    
                    Account account = new Account(accountId,accountHolderName,accountType,accountBalance,contactNumber,address,accountCreateDate,dob,accountStatus,null);
                    

                    return Optional.of(account);
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
            finally {
                if(connection.isPresent()){
                    dbUtils.closeConnection(connection.get());
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteAccountById(String accountId) {

        Optional<Connection> connection = dbUtils.getConnection();
        PreparedStatement preparedStatement = null;

        String delete = "DELETE FROM account WHERE accountId = ? ";
        try {

            preparedStatement = connection.get().prepareStatement(delete);
            preparedStatement.setString(1,accountId);

            int result = preparedStatement.executeUpdate();

            if(result > 0){
                return true;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connection.ifPresent(dbUtils::closeConnection);
        }
        return false;
    }

    @Override
    public List<Account> getAllAccounts() throws InvalidNameException, InvalidDateException {

        Optional<Connection> connection = dbUtils.getConnection();
        ResultSet resultSet = null;

        List<Account> accounts = new ArrayList<>();
        String getAll = "select * from account";

        if(connection.isPresent()){
            try {
                PreparedStatement preparedStatement = connection.get().prepareStatement(getAll);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                	
                    String accountId = resultSet.getString("accountId");
                    String accountHolderName = resultSet.getString("accountHolderName");
                    String accountType = resultSet.getString("accountType");
                    Float accountBalance = resultSet.getFloat("balance");
                    String contactNumber = resultSet.getString("contactNumber");
                    String address = resultSet.getString("address");
                    LocalDate accountCreateDate = resultSet.getDate("accountCreatedDate").toLocalDate();
                    LocalDate  dob = resultSet.getDate("dob").toLocalDate();
                    Boolean accountStatus = resultSet.getBoolean("accountStatus");
             
                    
                    Account account = new Account(accountId,accountHolderName,accountType,accountBalance,contactNumber,address,accountCreateDate,dob,accountStatus,null);

                    accounts.add(account);
                }
                return accounts;
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
            finally {
                connection.ifPresent(dbUtils::closeConnection);
            }
        }
        return null;
    }
}
