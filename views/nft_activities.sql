 WITH items AS (
         SELECT
                CASE
                    WHEN transfer."FromId" IS NULL THEN 'mint'::text
                    WHEN transfer."ToId" IS NULL THEN 'burn'::text
                    ELSE 'transfer'::text
                END AS "Type",
            fromacc."Address" AS "From",
            COALESCE(toacc."Address", fromacc."Address") AS "Owner",
            contract."Address" AS "Contract",
            token."TokenId",
            transfer."Amount" AS "Value",
            COALESCE(transaction."OpHash", origination."OpHash") AS "TransactionHash",
            block."Hash" AS "BlockHash",
            transfer."Level" AS "BlockNumber",
            transfer."Id" AS "LogIndex"
           FROM "TokenTransfers" transfer
             LEFT JOIN "Tokens" token ON token."Id" = transfer."TokenId"
             LEFT JOIN "Accounts" contract ON contract."Id" = token."ContractId"
             LEFT JOIN "Accounts" fromacc ON fromacc."Id" = transfer."FromId"
             LEFT JOIN "Accounts" toacc ON toacc."Id" = transfer."ToId"
             LEFT JOIN "Blocks" block ON block."Level" = transfer."Level"
             LEFT JOIN "TransactionOps" transaction ON transaction."Id" = transfer."TransactionId"
             LEFT JOIN "OriginationOps" origination ON origination."Id" = transfer."OriginationId"
        )
 SELECT items."Type",
    items."From",
    items."Owner",
    items."Contract",
    items."TokenId",
    items."Value",
    items."TransactionHash",
    items."BlockHash",
    items."BlockNumber",
    items."LogIndex"
   FROM items;