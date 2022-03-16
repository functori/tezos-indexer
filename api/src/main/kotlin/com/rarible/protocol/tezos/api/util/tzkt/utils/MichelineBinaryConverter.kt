//package org.rarible.tezos.client.tzkt.utils
//
//import java.io.ByteArrayInputStream
//
//class MichelineBinaryConverter {
//
//    class FormatException(msg: String) : Exception(msg)
//
//    fun read(bytes: ByteArray, depth: Int){
//        val reader = bytes.inputStream()
//        val tag = reader.read()
//        val tagVal = 0x80
//        if(tag >= tagVal){
//            val prim = MichelinePrim(prim = reader.read() as PrimType)
//            val args = (tag and 0x70) shr 4
//            if(args > 0){
//                if (args == 0x07){
//
//                    args = reader.read7BitInt()
//                }
//            }
//        }
//    }
//
//
//    fun read7BitInt(reader: ByteArrayInputStream): Int
//    {
//        var res = 0;
//        var bits = 0;
//        var byte = 0;
//
//        while (bits < 28)
//        {
//            byte = reader.read();
//            res = res or ((byte and 0x7F) shl bits);
//            bits += 7;
//
//            if (byte < 0x80) return res;
//        }
//
//        byte = reader.read();
//        if (byte > 0x0F) throw FormatException("Int32 overflow");
//
//        res = res or (byte shl 28);
//
//        return res;
//    }
//}