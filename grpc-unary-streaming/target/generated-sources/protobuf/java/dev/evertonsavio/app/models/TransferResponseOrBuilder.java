// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: transfer-service.proto

package dev.evertonsavio.app.models;

public interface TransferResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TransferResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.TransferStatus status = 1;</code>
   */
  int getStatusValue();
  /**
   * <code>.TransferStatus status = 1;</code>
   */
  dev.evertonsavio.app.models.TransferStatus getStatus();

  /**
   * <code>repeated .Account accounts = 2;</code>
   */
  java.util.List<dev.evertonsavio.app.models.Account> 
      getAccountsList();
  /**
   * <code>repeated .Account accounts = 2;</code>
   */
  dev.evertonsavio.app.models.Account getAccounts(int index);
  /**
   * <code>repeated .Account accounts = 2;</code>
   */
  int getAccountsCount();
  /**
   * <code>repeated .Account accounts = 2;</code>
   */
  java.util.List<? extends dev.evertonsavio.app.models.AccountOrBuilder> 
      getAccountsOrBuilderList();
  /**
   * <code>repeated .Account accounts = 2;</code>
   */
  dev.evertonsavio.app.models.AccountOrBuilder getAccountsOrBuilder(
      int index);
}
