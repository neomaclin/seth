package org.ethereum.seth

object CLI extends App {


  private val help =
    """
      |  --help                -- this help message
      |  -reset <yes/no>       -- reset yes/no the all database
      |  -db <db>              -- to setup the path for the database directory
      |  -listen  <port>       -- port to listen on for incoming connections
      |  -connect <enode://pubKey@host:port>  -- address actively connect to
      |  -connectOnly <enode://pubKey@host:port>  -- like 'connect', but will not attempt to connect to other peers
      |
      |  e.g: cli -reset no -db db-1 -listen 20202 -connect enode://0be5b4@poc-7.ethdev.com:30300
      |
      """.stripMargin

  if (args.isEmpty || args.contains("--help")) {
    println(help)
    System.exit(1)
  } else {

    val cliOptions = scala.collection.mutable.Map.empty[String, String]

    args.foldLeft("") {
      case ("-reset", option) => {
        if (!option.startsWith("-")) cliOptions.put("reset", option)
        option
      }
      case ("-listen", port) => {
        if (!port.startsWith("-")) cliOptions.put("listen", port)
        port
      }
      case ("-db", db) => {
        if (!db.startsWith("-")) cliOptions.put("db", db)
        db
      }
      case ("-connect", address) => {
        if (!address.startsWith("-")) cliOptions.put("address", address)
        address
      }
      case ("-connectOnly", address) => {
        if (!address.startsWith("-")) cliOptions.put("addressConnect", address)
        address
      }
      case (l, r) => r
    }

    println(cliOptions)

    System.exit(0)
  }

}



