SELECT
    collections."Address" as id,
    owners."Address" as collection_owner,
    collections."Metadata" as metadata,
    storages."JsonValue" as storage,
    scripts."ParameterSchema" as entrypoints
FROM "Accounts" collections
INNER JOIN "Accounts" owners ON collections."CreatorId" = owners."Id"
INNER JOIN "Storages" storages ON collections."Id" = storages."ContractId" AND storages."Current" = true
INNER JOIN "Scripts" scripts ON collections."Id" = scripts."ContractId" AND scripts."Current" = true
WHERE collections."Type" = 2 and collections."Kind" = 2 and collections."Tags" & 9 = 9