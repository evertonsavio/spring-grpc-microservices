// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: users-service.proto

package dev.evertonsavio.proto;

public interface UserResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:UserResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string login_id = 1;</code>
   */
  java.lang.String getLoginId();
  /**
   * <code>string login_id = 1;</code>
   */
  com.google.protobuf.ByteString
      getLoginIdBytes();

  /**
   * <code>string name = 2;</code>
   */
  java.lang.String getName();
  /**
   * <code>string name = 2;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>.common.Genre genre = 3;</code>
   */
  int getGenreValue();
  /**
   * <code>.common.Genre genre = 3;</code>
   */
  dev.evertonsavio.proto.common.Genre getGenre();
}
