 SELECT owners."Address" AS owner,
    contracts."Address" AS contract,
    tokens."TokenId",
    tb."Balance"
   FROM "TokenBalances" tb
     LEFT JOIN "Accounts" owners ON owners."Id" = tb."AccountId"
     LEFT JOIN "Tokens" tokens ON tokens."Id" = tb."TokenId"
     LEFT JOIN "Accounts" contracts ON contracts."Id" = tokens."ContractId";