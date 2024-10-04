package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString
public class ConnectionDetails {
    String ipAddress;   // ip address you are trying to connect to.
    String port;        // Port number you are trying to connect to.
    boolean doRetry;      // if connection should retry upon fail
}
