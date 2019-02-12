/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.6.39-log : Database - framedev
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`framedev` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `framedev`;

/*Table structure for table `act_evt_log` */

DROP TABLE IF EXISTS `act_evt_log`;

CREATE TABLE `act_evt_log` (
  `LOG_NR_` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_STAMP_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_` longblob,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_PROCESSED_` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`LOG_NR_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_evt_log` */

/*Table structure for table `act_ge_bytearray` */

DROP TABLE IF EXISTS `act_ge_bytearray`;

CREATE TABLE `act_ge_bytearray` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  `GENERATED_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_BYTEARR_DEPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ge_bytearray` */

insert  into `act_ge_bytearray`(`ID_`,`REV_`,`NAME_`,`DEPLOYMENT_ID_`,`BYTES_`,`GENERATED_`) values ('112502',5,'source',NULL,'{\"resourceId\":\"112501\",\"properties\":{\"process_id\":\"leave\",\"name\":\"请假审批流程\",\"documentation\":\"请假审批流程\",\"process_author\":\"jeesite\",\"process_version\":\"\",\"process_namespace\":\"http://www.activiti.org/processdef\",\"executionlisteners\":\"\",\"eventlisteners\":\"\",\"signaldefinitions\":\"\",\"messagedefinitions\":\"\"},\"stencil\":{\"id\":\"BPMNDiagram\"},\"childShapes\":[{\"resourceId\":\"sid-E955885D-3F13-4A1F-A038-2425208B77F8\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"executionlisteners\":{\"executionListeners\":[{\"event\":\"start\",\"implementation\":\"com.makun.worklistener.MyExecutionListener\",\"className\":\"com.makun.worklistener.MyExecutionListener\",\"expression\":\"\",\"delegateExpression\":\"\"}]},\"initiator\":\"\",\"formkeydefinition\":\"\",\"formproperties\":\"\"},\"stencil\":{\"id\":\"StartNoneEvent\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-51450949-2DC1-4675-A6FA-57FA33925137\"}],\"bounds\":{\"lowerRight\":{\"x\":94,\"y\":59},\"upperLeft\":{\"x\":64,\"y\":29}},\"dockers\":[]},{\"resourceId\":\"sid-37D69A34-86F5-4DAE-BB7D-75538792BD1C\",\"properties\":{\"overrideid\":\"task1\",\"name\":\"初步审核\",\"documentation\":\"请假的初步审核，测试变量设置流程处理人\",\"asynchronousdefinition\":\"false\",\"exclusivedefinition\":\"false\",\"executionlisteners\":\"\",\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"isforcompensation\":\"false\",\"usertaskassignment\":{\"assignment\":{\"assignee\":\"\",\"candidateUsers\":[{\"value\":\"${roles}\",\"$$hashKey\":\"0AZ\"}]}},\"formkeydefinition\":\"\",\"duedatedefinition\":\"\",\"prioritydefinition\":\"\",\"formproperties\":\"\",\"tasklisteners\":{\"taskListeners\":[{\"event\":\"create\",\"implementation\":\"com.makun.worklistener.MyTaskListener\",\"className\":\"com.makun.worklistener.MyTaskListener\",\"expression\":\"\",\"delegateExpression\":\"\"}]}},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-010FC4E6-BB5E-49E6-8C42-A7256157E060\"}],\"bounds\":{\"lowerRight\":{\"x\":239,\"y\":84},\"upperLeft\":{\"x\":139,\"y\":4}},\"dockers\":[]},{\"resourceId\":\"sid-51450949-2DC1-4675-A6FA-57FA33925137\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-37D69A34-86F5-4DAE-BB7D-75538792BD1C\"}],\"bounds\":{\"lowerRight\":{\"x\":138.15625,\"y\":44},\"upperLeft\":{\"x\":94.609375,\"y\":44}},\"dockers\":[{\"x\":15,\"y\":15},{\"x\":50,\"y\":40}],\"target\":{\"resourceId\":\"sid-37D69A34-86F5-4DAE-BB7D-75538792BD1C\"}},{\"resourceId\":\"sid-AB77561E-D313-41AA-8546-F88A442D893E\",\"properties\":{\"overrideid\":\"task2\",\"name\":\"第二步审核\",\"documentation\":\"测试流程变量的互相覆盖问题\",\"asynchronousdefinition\":\"false\",\"exclusivedefinition\":\"false\",\"executionlisteners\":\"\",\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"isforcompensation\":\"false\",\"usertaskassignment\":{\"assignment\":{\"assignee\":\"${inputUser}\"}},\"formkeydefinition\":\"\",\"duedatedefinition\":\"\",\"prioritydefinition\":\"\",\"formproperties\":\"\",\"tasklisteners\":{\"taskListeners\":[{\"event\":\"create\",\"implementation\":\"com.makun.worklistener.Wcw\",\"className\":\"com.makun.worklistener.Wcw\",\"expression\":\"\",\"delegateExpression\":\"\"}]}},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-010FD6DA-AB0D-448B-9795-BD0437C3FB20\"}],\"bounds\":{\"lowerRight\":{\"x\":384,\"y\":84},\"upperLeft\":{\"x\":284,\"y\":4}},\"dockers\":[]},{\"resourceId\":\"sid-010FC4E6-BB5E-49E6-8C42-A7256157E060\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-AB77561E-D313-41AA-8546-F88A442D893E\"}],\"bounds\":{\"lowerRight\":{\"x\":283.15625,\"y\":44},\"upperLeft\":{\"x\":239.84375,\"y\":44}},\"dockers\":[{\"x\":50,\"y\":40},{\"x\":50,\"y\":40}],\"target\":{\"resourceId\":\"sid-AB77561E-D313-41AA-8546-F88A442D893E\"}},{\"resourceId\":\"sid-AEB46284-89F1-4552-B833-11C9BE49DF9B\",\"properties\":{\"overrideid\":\"task3\",\"name\":\"最终审核\",\"documentation\":\"最终审核，查看流程变量设置的处理人是否生效\",\"asynchronousdefinition\":\"false\",\"exclusivedefinition\":\"false\",\"executionlisteners\":\"\",\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"isforcompensation\":\"false\",\"usertaskassignment\":{\"assignment\":{\"assignee\":\"${inputUser}\"}},\"formkeydefinition\":\"\",\"duedatedefinition\":\"\",\"prioritydefinition\":\"\",\"formproperties\":\"\",\"tasklisteners\":{\"taskListeners\":[{\"event\":\"create\",\"implementation\":\"com.makun.worklistener.MyTaskListener\",\"className\":\"com.makun.worklistener.MyTaskListener\",\"expression\":\"\",\"delegateExpression\":\"\"}]}},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-11BFF6A4-3DB3-4E5F-969A-F74A2A39BB27\"}],\"bounds\":{\"lowerRight\":{\"x\":529,\"y\":84},\"upperLeft\":{\"x\":429,\"y\":4}},\"dockers\":[]},{\"resourceId\":\"sid-010FD6DA-AB0D-448B-9795-BD0437C3FB20\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-AEB46284-89F1-4552-B833-11C9BE49DF9B\"}],\"bounds\":{\"lowerRight\":{\"x\":428.15625,\"y\":44},\"upperLeft\":{\"x\":384.84375,\"y\":44}},\"dockers\":[{\"x\":50,\"y\":40},{\"x\":50,\"y\":40}],\"target\":{\"resourceId\":\"sid-AEB46284-89F1-4552-B833-11C9BE49DF9B\"}},{\"resourceId\":\"sid-FEBC6139-1155-498C-BEF5-F0DDE395C296\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"executionlisteners\":{\"executionListeners\":[{\"event\":\"end\",\"implementation\":\"com.makun.worklistener.MyExecutionListener\",\"className\":\"com.makun.worklistener.MyExecutionListener\",\"expression\":\"\",\"delegateExpression\":\"\"}]}},\"stencil\":{\"id\":\"EndNoneEvent\"},\"childShapes\":[],\"outgoing\":[],\"bounds\":{\"lowerRight\":{\"x\":602,\"y\":58},\"upperLeft\":{\"x\":574,\"y\":30}},\"dockers\":[]},{\"resourceId\":\"sid-11BFF6A4-3DB3-4E5F-969A-F74A2A39BB27\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-FEBC6139-1155-498C-BEF5-F0DDE395C296\"}],\"bounds\":{\"lowerRight\":{\"x\":573.375,\"y\":44},\"upperLeft\":{\"x\":529.390625,\"y\":44}},\"dockers\":[{\"x\":50,\"y\":40},{\"x\":14,\"y\":14}],\"target\":{\"resourceId\":\"sid-FEBC6139-1155-498C-BEF5-F0DDE395C296\"}}],\"bounds\":{\"lowerRight\":{\"x\":1200,\"y\":1050},\"upperLeft\":{\"x\":0,\"y\":0}},\"stencilset\":{\"url\":\"stencilsets/bpmn2.0/bpmn2.0.json\",\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"},\"ssextensions\":[]}',NULL),('112503',2,'source-extra',NULL,'�PNG\r\n\Z\n\0\0\0\rIHDR\0\0�\0\0\0�\0\0\0W7ۙ\0\0\0 cHRM\0\0z&\0\0��\0\0�\0\0\0��\0\0u0\0\0�`\0\0:�\0\0p��Q<\0\0\0gAMA\0\0��|�Q�\0\0\0sRGB\0���\0\0\0bKGD\0�\0�\0�����\0\0\0	pHYs\0\0�\0\0��+\0\0�IDATx���tTս��dȃG���E��j��(��%5`�WՈV[�>��k\nK_K�<������-T�\r�I�ZH�#			�d�����N���d2���5k��d29�?{���\0\0\0\0\0\0\0\0\0\0\0\0\0\0�aԇ��޾}�o\nG9r䂢�����rL}?�aaި������?>9>>�o��!b�	#�[@߷��KJJZv�����ř6mژ��pN=�����)((0�������6�\Z<xp1b�\"�@fa�Νi۶m��K�.��3�TK��҈rrrLvvv���9p��%��!b��?EpP�B��n�ٳ��С���Ǥ]�v-���wèQ�2����M�\"��!�0\" i�Ƕ0Q!{�Ϗ�5�����K���3Gm7Q�:\ZOT,��\"##UXG���ILL\\�����1DC�&y��4�\\s�4�SS!�k�[�p�צ��񖗚#���3MI�n`hժ��ڵkyDDī��!b�	#�U�M,�\\��9��SSz���Uh�߾��@:w�����|��ˉ!C�1FmY�U�5�*>E�]�~4��;�ڻ��!C�1F�o�-+j�-;^�/{��!�o��,{7�1DC��\0���p�JMϞ�3�]b��!�=��5m��f�����y�C �@���J���j��\'�C �\0F��v]�0��V\'<�\"ƴ;\'�b�@P׺COӪ�\'}�Mǋ8@ �@$��1eǋOx���a�!C\0	#Bݱ��+)~�����bv~1�y@�H��s��Bz�s�֚��a��M5�v�1^�B1bhVX�z�����C��^�4��ض�;���l�b�1:zQgj�׶����?�����/@C �\0F;����[^�1bh&�F��4��\0b��a\0\0\0	#\0\0\0H\0\0@�\0\0�����Z�z�љ����嗷�ٳ�ONNN˂�OII��x<�6m�o׮�����5���=���_s�\0\0h�;�����~���:�[k{+��\\�l�?�,\"\"\"uΜ9ߒ0�(F����Ə?�����={����>���DGG�4�>|8r׮]ݲ����_��ֱc��,**\Z?mڴE\0\0���#G�w�����	a�I�mo]�Ϻ��aeee/�1b��~�ܹsSHC�ڵk�0a����Q6L�޽�mI�96iԭC��\'?������6n�xNjj�III�<x�,��#\n\0@`�����7�^��:��\0�{�k$���`��I��\'���{�Ϛ5��n�����??i�x*J.�-bٲe=�ϟ�ս�����\0\0�������o�����>�z��?���ׯ��޽�i۶�iӦ�9r����3���fݺuf������������r�ȑc������Z��jժ5+V����6�~�^o����G�1�&Mz��1c��e\0\0&Yj���,F���:����:g䰺�-[:�3�<�\\q�N�t�R��G���2=���%޶Ic�M\Z����U�>Գ�hѢK�~��z\'��s�9�<��16 \'�\0\Z�Q\0  �E�,���I�N�8Q^N�,��z�|���7��kN�:�����,���>�`����zO>�d�-Z�f�G\0���9�o���\\`�y�*I���������7i�\ZLu>	�Njtzz�T�Yl������h[3Q�5�?q\0\0�0�	���YT��#�<��Q�\rU��w��M:5<=��1�������8�sؘ�׍�i\0\0���α��5�Zs���7Y�M\Z5R�X6>X���Oջ���u���iѢE�������l�恦�_����>(`��!C����������5��\r:�7�H\n���X]�e߾}m��_|qdTTT7͟HMM�����\r\\s��ڿ�6���t��\n�����\r������&22ҹ��	\"C�~���ܹs��rrr����Fl�V9h�r��c�S�NE			iZ�\\��\\�1�Z_�+����_o>��Cw˝�&��T�G�.�WӦ܍�W�^��nxS�϶���F��G���˶�f���w�qGt ���S�\Z�����cǎ�� ���:_ko�q����oM�6�K���_��������?���&�bH���sΟn��_͚5k̛o��T�2w�\\c+�:�\rb+tˡI�&i�n���������K��+V+�����DŐ��wۤ,��bH��s����U�\Z�VO�b>�7���=��6�.��O���k���Sק�\0�v[���~y[III���ͳ�-��僕+W�6�{��[�l����l�����O?5?���*���rU�~���o��^���x��]��l�ϟ_�2��n߾�J���7ި��ꫯ��7�ltIȊD_-�*�����4�u�-[fn��&g��K�4�fϞm~��_�Y�fi�S���\'��/4������U�VN�����j(���F����y�ת�@l�n9d߃���[͆\r*S�r�G?�Q���\\�v�iιt�����v�E	�M�7�XiP��6���hC%6q�o�_\\=�*�\r���\Z�\r��m]�~u>	c=���T��O�{nK󟋙�{��~��R�-�?��YQQQ+me\\�7�e��y����bV�^�|��Qo��F�k%�[��T��R��>W�/�cǎ��^	iAA�4h��-o�d@�����/�k�.���?��)����r^gذa��+��5�t�tί��*�`�s�y�9�E�ؕ�)�����;]��/���\Z��5�����z�KC��۷w�¥aF=���\'611�G�vΝ\Z&�G��8[��\'k�֭U�N+�&�m��*�1��k=+��1��S7��ڵ��}��A\r���~8=�:�\'[�t��\\j���\0>4q��`�g�\n������}��_���6r�7�7o��2eJ���:%�z\\�C������5}�t������$t�Ν�=*�O?���a���t��.*��3��[oi���KNNv��ԺW��aQٿ�34\Z�1�Kw;�+�2p���ݻ�ޗ��:��]Ԃ6�{��֭[����9����ǘ�桞>�z�5�I����J��)��q�����>+a�1c��\'�x��5#�C~�ynch�b�*w��u��oO���)�|{�a�ڵ�ױ���r���=O���F�E��ÇMFF��V��q�\'<OC@�|�Me��J^�����?���T�]�tq\nd]��ꫝ��&++�I4܂Z��;,��aժU�{P���L���*��K/�dƏ���!R\r��z���R�����v{\nE��u݌?�c�:55:>��3���/�pΡ�D7�;x�s�h�\"�2�굾�⋝�_z��ϔ,���o]C9�d*{\Zj+�S�O>�M3�	cTT��&�a�L\Z/���.lk�	~�wk�vW�DV+������\\�Q�����;�J�ݢ�w��;���-�ǌS���c��E-�_��W\'�d�?W���Q�����W:ڴi����ر�ӢV��Q���;�M�	�N����S;v�Pa��P�!�ʄ	�5�8۪9��7Vt����*�c�:5Mw�yP���u�4\'MC��H�C�\\��u��<%�.Ňz\"�\Z����2����C�Ce6��Vt���P�}�9��h̤Q��}��H�9��=����;I�����A�%�������	XM�b}��{�rh�}��~����I�ǚ;t�]wy��>Ջ�\n^����׿vZ�111�\\ W��\'�L��ݺusZ�9s�8�W};\n\r��U�\"p{��|��U�k�PBB��ޏz��t���$��/��\\j��ރ�|B>�tԓ���=zԹ���++����Z]Zۡ��1�����9�ӧO�yW��� �G�2Vh>�����G_%��_�^j\rm+�ӜE5<ܲ�r��bHS�t*l�(~\"��	1TRR�i�]��T��L��>��06sm۶�i?����0fffnU막�u�Wfo��-F���4�7�&g+l��oL-h��}�]��ɓ\';��V���ѕ��]��Vʪ`vW��Kjy�~��\'��\r��&���Ӗ+�����n��������ip�ŋ;��4]�,|{\rBD@Đ��U����Z=8����S)))�?�i�^��\n�rȥ��j��^����S����5_�]x��U��􇯿��r��<��N�8s�Lb�a��Oc|zk�!�lnp�m۶5��ݾ|�(k	c��a\\���ս_�~~��k֬QyU��}>X�m�m�~������5좕�n�\nQ����\Z�񥭒�`F-���ӕQ\\�$�P�M(w�\n%.ML�����O�U��=r�1�.�҂��K�:�ӊ��ʒ%K��t�6n�xʍ�����횔d�7��\n�rHu����1V�N���O	Z����Tg�����c-Ț8q\"��8\"Hy��U6�t�DT���X��ª�~�C>a��ϟh?�w�����i��-[��0>�&�7u�ۻ��Ҝ]�UE^ �#��5�9d�ZB�\0���\Z2��\\��iݫ7@�ZԔ���S�B�ߝ�ۓY�z4!^D?���ki�鷿��y�WB�sH1�DQ�/��Q��}�D�����~���V�T���q+TGl�N9��F匶a��z�\\�TcBM���ihY{(���JC���W_}�<��C梋.\"V�0�<O���_җj<j�@clޭ�CFF�:��*ZH���ٖ�և~x�\r�����(������9Mt���m�*۶�bObuZu�J_����>�1�j����ߘZ���A�V(��V�\nx��l��B\\��5�-[T0��+�9D\ZJj�K����!U��ߊU߫7Z�T���#X�K�[�Y��y��*�x��F5�2Y+��M��Z<�s��d3��P�o�H��?��̙�m���� �8:�t.�vk�\r7Y\\��/H�X!??�;V�޽=��˘7{��m�Oi������t^ܽ�|/�.�T}�~��](��>n���纽X�,�b�w�vW�x��uw���-�|��lCl%h�QZȢ!db% b�u{svGW¨q\r9�Q������E[��%�{�_KZfΜ���7+p\Z��ɓջX�gϞT�:\0\0�7w��4{�R_����_vz��zmR_f�����`��I+���߸x��bm��233���ȸ��4�i�k�\0�4����FS^|��z\'�Jm��ݽ{�;TYLu>	c�/8v��=6h�4���l�|6iҤ>6p�-���h\0дu���}�~�$sZ̦\r�5�\\��.���t{5=&��|F)))s\n|ꩧ\n��Q=��=���eeeSm�p�\0hz����P�=nҨ��q��9�W�t�F�VJO<�oϢVp��:�E/��@y+!!�ȳ�>���ÇG:�U��M�2%���?�m������\0 p�do����zjXXX[�i�Jx�i�ݻ���RW�ޚ�*����$jS�ʏ=��7�gQ�A>�u>	c\r��#F��?�L4%%%�^r�%1�����˗�%\'\'w/--�n�j��\0�:���ӵݎS�M��kӟ��H�j�Y�:����VG������ɓ\'�����գG�����[w��-�]�v��MBqq�^���ߴiӖիWGlٲ�h�Z�빹�),p\0�Y��תηI_�M��i����:�����H��/LLL�q���6l��~���ԅ�+����q{ӞK��ۘ`ؠ\0�P����ˇ��ѭ�;U������Z׆����J�O�XK��޽\\q\0\0��!�U�\0\0\0 a\0\0\0	#\0\0\0H\0\0@�\0\0\0F\0\0\0�0\0\0��\0\0\0$�\0\0\0\0	#\0\0\0H\0\0@�\0\0\0F\0\0\0�0\0\0���;X�ý����\0`��{WJ�1F����}��@�C�eٻ\r��!C aD@iѢ����|D\0���-�w+�!C �@�RRR���={�{�^F��>x�`o{��1b$�(���_g�rrr8M(++k�=9�^{�Zb��!�0\"[������p0�@^^�۪�Eyy�Xb�1D�_\"8�!RRR��=z݁n���������������v�u�]��1DC aD�JNN�6jԨ/���;�\ZE���1���x����dggT!=hР�!C�1�Ӆ:[�lY����W<�%:t��}��g��~\'���������*DM,�\\!\r�<8�1DC aD�����Ƕ8o�_�����Q�7m����V�ۢ`�XNC�1\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0����#!��	\0\0\0\0IEND�B`�',NULL),('127546',1,'请假.bpmn20.xml','127545','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/processdef\">\n  <process id=\"leave\" name=\"请假审批流程\" isExecutable=\"true\">\n    <documentation>请假审批流程</documentation>\n    <startEvent id=\"sid-E955885D-3F13-4A1F-A038-2425208B77F8\">\n      <extensionElements>\n        <activiti:executionListener event=\"start\" class=\"com.makun.worklistener.MyExecutionListener\"></activiti:executionListener>\n      </extensionElements>\n    </startEvent>\n    <userTask id=\"task1\" name=\"初步审核\" activiti:candidateUsers=\"${roles}\">\n      <documentation>请假的初步审核，测试变量设置流程处理人</documentation>\n      <extensionElements>\n        <activiti:taskListener event=\"create\" class=\"com.makun.worklistener.MyTaskListener\"></activiti:taskListener>\n      </extensionElements>\n    </userTask>\n    <sequenceFlow id=\"sid-51450949-2DC1-4675-A6FA-57FA33925137\" sourceRef=\"sid-E955885D-3F13-4A1F-A038-2425208B77F8\" targetRef=\"task1\"></sequenceFlow>\n    <userTask id=\"task2\" name=\"第二步审核\" activiti:assignee=\"${inputUser}\">\n      <documentation>测试流程变量的互相覆盖问题</documentation>\n      <extensionElements>\n        <activiti:taskListener event=\"create\" class=\"com.makun.worklistener.Wcw\"></activiti:taskListener>\n      </extensionElements>\n    </userTask>\n    <sequenceFlow id=\"sid-010FC4E6-BB5E-49E6-8C42-A7256157E060\" sourceRef=\"task1\" targetRef=\"task2\"></sequenceFlow>\n    <userTask id=\"task3\" name=\"最终审核\" activiti:assignee=\"${inputUser}\">\n      <documentation>最终审核，查看流程变量设置的处理人是否生效</documentation>\n      <extensionElements>\n        <activiti:taskListener event=\"create\" class=\"com.makun.worklistener.MyTaskListener\"></activiti:taskListener>\n      </extensionElements>\n    </userTask>\n    <sequenceFlow id=\"sid-010FD6DA-AB0D-448B-9795-BD0437C3FB20\" sourceRef=\"task2\" targetRef=\"task3\"></sequenceFlow>\n    <endEvent id=\"sid-FEBC6139-1155-498C-BEF5-F0DDE395C296\">\n      <extensionElements>\n        <activiti:executionListener event=\"end\" class=\"com.makun.worklistener.MyExecutionListener\"></activiti:executionListener>\n      </extensionElements>\n    </endEvent>\n    <sequenceFlow id=\"sid-11BFF6A4-3DB3-4E5F-969A-F74A2A39BB27\" sourceRef=\"task3\" targetRef=\"sid-FEBC6139-1155-498C-BEF5-F0DDE395C296\"></sequenceFlow>\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_leave\">\n    <bpmndi:BPMNPlane bpmnElement=\"leave\" id=\"BPMNPlane_leave\">\n      <bpmndi:BPMNShape bpmnElement=\"sid-E955885D-3F13-4A1F-A038-2425208B77F8\" id=\"BPMNShape_sid-E955885D-3F13-4A1F-A038-2425208B77F8\">\n        <omgdc:Bounds height=\"30.0\" width=\"30.0\" x=\"64.0\" y=\"29.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"task1\" id=\"BPMNShape_task1\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"139.0\" y=\"4.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"task2\" id=\"BPMNShape_task2\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"284.0\" y=\"4.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"task3\" id=\"BPMNShape_task3\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"429.0\" y=\"4.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"sid-FEBC6139-1155-498C-BEF5-F0DDE395C296\" id=\"BPMNShape_sid-FEBC6139-1155-498C-BEF5-F0DDE395C296\">\n        <omgdc:Bounds height=\"28.0\" width=\"28.0\" x=\"574.0\" y=\"30.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-51450949-2DC1-4675-A6FA-57FA33925137\" id=\"BPMNEdge_sid-51450949-2DC1-4675-A6FA-57FA33925137\">\n        <omgdi:waypoint x=\"94.0\" y=\"44.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"139.0\" y=\"44.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-11BFF6A4-3DB3-4E5F-969A-F74A2A39BB27\" id=\"BPMNEdge_sid-11BFF6A4-3DB3-4E5F-969A-F74A2A39BB27\">\n        <omgdi:waypoint x=\"529.0\" y=\"44.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"574.0\" y=\"44.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-010FD6DA-AB0D-448B-9795-BD0437C3FB20\" id=\"BPMNEdge_sid-010FD6DA-AB0D-448B-9795-BD0437C3FB20\">\n        <omgdi:waypoint x=\"384.0\" y=\"44.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"429.0\" y=\"44.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-010FC4E6-BB5E-49E6-8C42-A7256157E060\" id=\"BPMNEdge_sid-010FC4E6-BB5E-49E6-8C42-A7256157E060\">\n        <omgdi:waypoint x=\"239.0\" y=\"44.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"284.0\" y=\"44.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>',0),('127547',1,'请假.leave.png','127545','�PNG\r\n\Z\n\0\0\0\rIHDR\0\0d\0\0\0^\0\0\0�o^\0\0gIDATx��}lTU\Z�IԨ1Ƙh�q5�q�Ә׸\Z�j�()�Q�JZE�\nq�)�ĢQ��dՐ���5��.ۅ~�R�ؔ.��._Bm�.[��Z��=�ݞ��ӏ�{���K�t��;3��g��=��3\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0��s�c�6�ݻ����ZS]]Md8jjj̎;�c#��4����4D&���h�����\'���.���p֚��h�@C\Z�f�E�	���mK�\Z\"���\0�\Z�������40���(TFh���4@A6b��}�9��cs�f�z�m�F���\ZBC\0�#��n7�*�����CB۴#��!4���\0��O��0tq���d���\ZBC\0�n#<�}ͨF�}�\r�!4��\0 �Fx�zŨF�}�\r�!4��\0\0#����\n2�hd��i4#�>�d���\ZBC\0�f#l���Q�P�00�)\ZBCh\r@���L{�9TU2|��n�>�d���\ZBC\0�f#T?�~�j��!4���\0d��ɴ����a�M�00�)\ZBCh\r@\Z�P+`����s7��U�I�h\r�!4\0�0B���h�3����QMЅ�ѱ�RI�h\r�!4\0Sd�c�Fi�b�h\r�!4\0i6�TZ�k�bh$S4���\Z�I\Z�DM��F2ECh\r�!\0��!\ZBCh\r\0F��!\rh�����\ZBCh\00B�\rh�@Cu�1W���~��\r���g������<3g���.<���˗/?�f͚\r6~KAF`�h\r�!4������*\'\'g���l���Qη�g��Wf���㨒2T�m޼y��U����̇~h���k���M__����͗_~il�f^x�/��≂���dF���\ZBC��O<�j��X*���jJ������Z�t�Wl�?ޤJss�Y�l�����#�g��\r��!4���Px�E�-���6�\"l�h�R�C�إ���u�<�ٲe˸\n�d�m�f���{��[�b�h\r�!\"jYNNΜ�s���_\\���7�x���ՙo������^^?w�7J�k�.�v�Z��O&e=��MQ1V__�oѢE����L\'N�0���?�sd�\r�!4��BU�=d���BlÆ\r�T�T����������}��TU�@=c*�R=���+((���#��\ZBCDT\n2�����ŋ{�aA����������9c\Z������zʞz�n{�fR�$S\r�!4�3��,))IKN\rg�^�:y�r&���*�X�re�挥��������=dɔ@Ch\r�-�j�=c�-��EYROY#U�8(++������٤�tfk7&F��!4���lא������0�ņ/�s��G������������K�b�h\r�!\"Y�+�4!?lܸ�^���5�2�[�����N�_�I�6~�b�h\r�!\"�\Z���n\"�z�Μ9��<��/��ĸ��kt;�u�֙L�v��f�r!,߁�[�:\'\'�w!F���\Z\"�����!yǽ��������%*�1н)u;�L�{����l	��}�u�VF��!4���l���)�����i��Z<���l��\Z�(���#�Ygggܞ�S!q���3F��!4���l����½�S=�?����?NQq�A^^^�F�B��;Ƈ]��V��1B�\r�!4Dd��l���S���Ŗ��}vW\n\'0&y�@#W�!\rhh��\\O�5��ͻ�������,�!ۯ�|����5T��z���	�vϞ=�����*��u�{�7�1�Uu�{zz�m�L����۽s�s�ߴ;_�hg�?+*�BC��ٳg�s1k�,�qŋ��rs��W�^�Îokk�?t�P��.\\�\n���z>=d!eѢE���C����+�sȆ`�s7d4����믿�L��G��!�c�kllL�u�Vo������>�`b�3HECC���\\o��&s�]wy��7o6��z�F�K�kkk�ж����c��3�!��eee��+�4�ׯ7�^{m�j�4����:ujض(j\r�� ���{�d�VTT4fQ��w�qG�5�O�Ƈ�s�BLqqq[�������GH��ܣ��\r0#T»��=C*--���ɓ	3ڴiS���>$i^v�e�c����k�g�}�x~��{���}^y啄!+��5���Z�𚚚�Ǻ�FE��wf�\Za \Z�9P�~��Q�qKK����SRK~���K�.!JO�=���i\r�ZSJ�BE�΍O?���TE�zG��>��;�ڧ��i\nFC\\eb�z�u�^�����\"d됍�\r��T\\r<p�@��4���L+-k������\n8W�����Ǉ$Z���Lş���X�y�7��h�+���P+7��;͌00\r�ģ�I�4���n�\\���q\"�em�����I�*�c�!�\Z*��W��UUU�.�W��c��eNC�Cb^}��_[��%�+��͝;�h6���._��K��=����Ԝ�Rr=dJ���!t�W�\Z��[�:�od2O7���*����a��\r��5�_�����!Sg�ܲ$8\r�<�0�L*�\\o�\n��dQ�\Z\Z����HY�N�z�zTU�i\Z�\"�&���3g&\ntw~]O�H>��R�Ϳ�X�?�]`��q�x��g�^�������V�.���1g>�����;�4<���c�C�n��	����8yxA��瞄Ѻ�ij�j���O>�$�~2JM�u�o�>�~xa0\Z�����Z�ME�AԴ��R?�/��rb��\n!��\Zi�\Z�\Z������5\"�{��|=V���P0p/����O�^�d�@zɺm˪)[���I#�ɸ�3�r]�΀�>7|��$n���|t���g�0�5y>�L�\r%_���Gy��V�Z5dC��Z�#M�ӯ!%?}�:G:�����\'&F��8�ψ���P�����{�\'�U��X���U��$5&�M�YҪ߳�@\n�G\\��^���ܯ�����oa���8(,,lя$�h��*��H`�ï��\\\ng�n���	�2�뮻nHQ�d�I���h4�U+6�e���z5���K.��;F��\\��_��!7�Н�]=e��5��Qq�sUm���2W��K���w�����HP�$͹F�hˤ�C��7��\n&5�j	��%K�����ek����b���ښ�b�����gt�Jyf�~GA^��\n�\"��i��7���;ֿ�Z���ܵTS5]�2��1�?�>C	Y��ܾ���0s\Z�di7����i�z������n�I�����;�3dvT���ƞC�z�T�����8�Fn���j]7w��V�H���ɳ4�}�x��#���r�SIIɤ�2�`����yc=ٜ�%�����x<>�Ř���?����mDA憆��LO� �ǣ�\Z�t�ԋ!�SKQs�R]��or�C	#�a}�C���z�2�B%��?4_LIQ	ӟ\0u.�����^aum���<h������M�p�W�^G{/��_��C����3�M�w=e���z���,����=S�S��1c��/���&̷,��5wRy�L��C2\r^�A[h(Xm�C��)��e�S���\Z�I�jJM���SD!燦j��bg���\'1ѿ����XÔ:�Q�^��7�ECh\rQ*�\\η��/�T���]�Ǫ��\rg�7R+������7����%��\0=Q���\Z_�omaaagSSS�x�����h������c�#DCh\rQ+�\\��/�1�hd�XzO��*����⥥��w��y�V���Q��MYSS��+Vl�U�1�U�Q��#��\ZBCD2�,��S�E1���kOR��}�������i��S�[#Dy5^�#DCh\rQ.����$S\r�!4\0!F���\Z\"(�\00B�dJ�!4��\0\0#��\ZBC\0FH�L	4���\0`�!\ZBCh�� �	�)���\Z\0�#DCh\rd\0!A2%�\ZBC\0�b�h\r�!��\0#$H�\ZBCh\00B�\r�!4DP�`�ɔ@Ch\r\0F��!4��\n2\0�� �h\r�!\0�1B4���AA�$S\r�!4\0!F���\Z\"(�\00B�dJ�!4��\0(�0B�\r�!4DP�`�ɔ@C�;40����ŀ�}����40�رcGG<ǄB����Fx\rh�@C\0ӌ���Y������w��Qp-R�`MM�I��!\rh`\Zb|��~uSk�\0����~$�M\r�!4��\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\0�2w+��\0\0\0\0IEND�B`�',1),('130006',1,'var-roles',NULL,'��\0sr\0java.util.ArrayListx����a�\0I\0sizexp\0\0\0w\0\0\0t\0 c9e3202c3f9a4b5da6d4c8aede8ac807x',NULL),('130008',1,'hist.var-roles',NULL,'��\0sr\0java.util.ArrayListx����a�\0I\0sizexp\0\0\0w\0\0\0t\0 c9e3202c3f9a4b5da6d4c8aede8ac807x',NULL),('130018',1,'var-roles',NULL,'��\0sr\0java.util.ArrayListx����a�\0I\0sizexp\0\0\0w\0\0\0t\0 0ee035ff27f540f78a3c8ec515a22db1t\02t\0 c9e3202c3f9a4b5da6d4c8aede8ac807x',NULL),('130020',1,'hist.var-roles',NULL,'��\0sr\0java.util.ArrayListx����a�\0I\0sizexp\0\0\0w\0\0\0t\0 0ee035ff27f540f78a3c8ec515a22db1t\02t\0 c9e3202c3f9a4b5da6d4c8aede8ac807x',NULL);

/*Table structure for table `act_ge_property` */

DROP TABLE IF EXISTS `act_ge_property`;

CREATE TABLE `act_ge_property` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ge_property` */

insert  into `act_ge_property`(`NAME_`,`VALUE_`,`REV_`) values ('next.dbid','132501',54),('schema.history','create(5.22.0.0)',1),('schema.version','5.22.0.0',1);

/*Table structure for table `act_hi_actinst` */

DROP TABLE IF EXISTS `act_hi_actinst`;

CREATE TABLE `act_hi_actinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_actinst` */

insert  into `act_hi_actinst`(`ID_`,`PROC_DEF_ID_`,`PROC_INST_ID_`,`EXECUTION_ID_`,`ACT_ID_`,`TASK_ID_`,`CALL_PROC_INST_ID_`,`ACT_NAME_`,`ACT_TYPE_`,`ASSIGNEE_`,`START_TIME_`,`END_TIME_`,`DURATION_`,`TENANT_ID_`) values ('130003','leave:1:127548','130001','130001','sid-E955885D-3F13-4A1F-A038-2425208B77F8',NULL,NULL,NULL,'startEvent',NULL,'2018-12-21 18:25:57.133','2018-12-21 18:26:24.202',27069,''),('130009','leave:1:127548','130001','130001','task1','130010',NULL,'初步审核','userTask',NULL,'2018-12-21 18:26:24.202',NULL,NULL,''),('130015','leave:1:127548','130013','130013','sid-E955885D-3F13-4A1F-A038-2425208B77F8',NULL,NULL,NULL,'startEvent',NULL,'2018-12-21 18:28:00.891','2018-12-21 18:28:00.897',6,''),('130021','leave:1:127548','130013','130013','task1','130022',NULL,'初步审核','userTask','0ee035ff27f540f78a3c8ec515a22db1','2018-12-21 18:28:00.897','2018-12-21 18:29:11.762',70865,''),('130033','leave:1:127548','130013','130013','task2','130034',NULL,'第二步审核','userTask','2','2018-12-21 18:29:11.762',NULL,NULL,'');

/*Table structure for table `act_hi_attachment` */

DROP TABLE IF EXISTS `act_hi_attachment`;

CREATE TABLE `act_hi_attachment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `URL_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CONTENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_attachment` */

/*Table structure for table `act_hi_comment` */

DROP TABLE IF EXISTS `act_hi_comment`;

CREATE TABLE `act_hi_comment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MESSAGE_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `FULL_MSG_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_comment` */

insert  into `act_hi_comment`(`ID_`,`TYPE_`,`TIME_`,`USER_ID_`,`TASK_ID_`,`PROC_INST_ID_`,`ACTION_`,`MESSAGE_`,`FULL_MSG_`) values ('130029','comment','2018-12-21 18:29:11.738',NULL,'130022','130013','AddComment','王成文处理任务啦','王成文处理任务啦');

/*Table structure for table `act_hi_detail` */

DROP TABLE IF EXISTS `act_hi_detail`;

CREATE TABLE `act_hi_detail` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`),
  KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`),
  KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_detail` */

/*Table structure for table `act_hi_identitylink` */

DROP TABLE IF EXISTS `act_hi_identitylink`;

CREATE TABLE `act_hi_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_identitylink` */

insert  into `act_hi_identitylink`(`ID_`,`GROUP_ID_`,`TYPE_`,`USER_ID_`,`TASK_ID_`,`PROC_INST_ID_`) values ('130002',NULL,'starter','1',NULL,'130001'),('130011',NULL,'candidate','c9e3202c3f9a4b5da6d4c8aede8ac807','130010',NULL),('130012',NULL,'participant','c9e3202c3f9a4b5da6d4c8aede8ac807',NULL,'130001'),('130014',NULL,'starter','1',NULL,'130013'),('130023',NULL,'candidate','0ee035ff27f540f78a3c8ec515a22db1','130022',NULL),('130024',NULL,'participant','0ee035ff27f540f78a3c8ec515a22db1',NULL,'130013'),('130025',NULL,'candidate','2','130022',NULL),('130026',NULL,'participant','2',NULL,'130013'),('130027',NULL,'candidate','c9e3202c3f9a4b5da6d4c8aede8ac807','130022',NULL),('130028',NULL,'participant','c9e3202c3f9a4b5da6d4c8aede8ac807',NULL,'130013');

/*Table structure for table `act_hi_procinst` */

DROP TABLE IF EXISTS `act_hi_procinst`;

CREATE TABLE `act_hi_procinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `END_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_procinst` */

insert  into `act_hi_procinst`(`ID_`,`PROC_INST_ID_`,`BUSINESS_KEY_`,`PROC_DEF_ID_`,`START_TIME_`,`END_TIME_`,`DURATION_`,`START_USER_ID_`,`START_ACT_ID_`,`END_ACT_ID_`,`SUPER_PROCESS_INSTANCE_ID_`,`DELETE_REASON_`,`TENANT_ID_`,`NAME_`) values ('130001','130001','leave','leave:1:127548','2018-12-21 18:25:57.133',NULL,NULL,'1','sid-E955885D-3F13-4A1F-A038-2425208B77F8',NULL,NULL,NULL,'',NULL),('130013','130013','leave','leave:1:127548','2018-12-21 18:28:00.891',NULL,NULL,'1','sid-E955885D-3F13-4A1F-A038-2425208B77F8',NULL,NULL,NULL,'',NULL);

/*Table structure for table `act_hi_taskinst` */

DROP TABLE IF EXISTS `act_hi_taskinst`;

CREATE TABLE `act_hi_taskinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_TASK_INST_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_taskinst` */

insert  into `act_hi_taskinst`(`ID_`,`PROC_DEF_ID_`,`TASK_DEF_KEY_`,`PROC_INST_ID_`,`EXECUTION_ID_`,`NAME_`,`PARENT_TASK_ID_`,`DESCRIPTION_`,`OWNER_`,`ASSIGNEE_`,`START_TIME_`,`CLAIM_TIME_`,`END_TIME_`,`DURATION_`,`DELETE_REASON_`,`PRIORITY_`,`DUE_DATE_`,`FORM_KEY_`,`CATEGORY_`,`TENANT_ID_`) values ('130010','leave:1:127548','task1','130001','130001','初步审核',NULL,'请假的初步审核，测试变量设置流程处理人',NULL,NULL,'2018-12-21 18:26:24.202',NULL,NULL,NULL,NULL,50,NULL,NULL,NULL,''),('130022','leave:1:127548','task1','130013','130013','初步审核',NULL,'请假的初步审核，测试变量设置流程处理人',NULL,'0ee035ff27f540f78a3c8ec515a22db1','2018-12-21 18:28:00.898','2018-12-21 18:28:46.179','2018-12-21 18:29:11.758',70860,'completed',50,NULL,NULL,NULL,''),('130034','leave:1:127548','task2','130013','130013','第二步审核',NULL,'测试流程变量的互相覆盖问题',NULL,'2','2018-12-21 18:29:11.762',NULL,NULL,NULL,NULL,50,NULL,NULL,NULL,'');

/*Table structure for table `act_hi_varinst` */

DROP TABLE IF EXISTS `act_hi_varinst`;

CREATE TABLE `act_hi_varinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_NAME_TYPE` (`NAME_`,`VAR_TYPE_`),
  KEY `ACT_IDX_HI_PROCVAR_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_varinst` */

insert  into `act_hi_varinst`(`ID_`,`PROC_INST_ID_`,`EXECUTION_ID_`,`TASK_ID_`,`NAME_`,`VAR_TYPE_`,`REV_`,`BYTEARRAY_ID_`,`DOUBLE_`,`LONG_`,`TEXT_`,`TEXT2_`,`CREATE_TIME_`,`LAST_UPDATED_TIME_`) values ('130004','130001','130001',NULL,'days','integer',0,NULL,NULL,2,'2',NULL,'2018-12-21 18:25:57.134','2018-12-21 18:25:57.134'),('130005','130001','130001',NULL,'inputUser','string',0,NULL,NULL,NULL,'2',NULL,'2018-12-21 18:26:07.829','2018-12-21 18:26:24.213'),('130007','130001','130001',NULL,'roles','serializable',0,'130008',NULL,NULL,NULL,NULL,'2018-12-21 18:26:24.192','2018-12-21 18:26:24.192'),('130016','130013','130013',NULL,'days','integer',0,NULL,NULL,2,'2',NULL,'2018-12-21 18:28:00.891','2018-12-21 18:28:00.891'),('130017','130013','130013',NULL,'inputUser','string',1,NULL,NULL,NULL,'0ee035ff27f540f78a3c8ec515a22db1',NULL,'2018-12-21 18:28:00.891','2018-12-21 18:29:11.768'),('130019','130013','130013',NULL,'roles','serializable',0,'130020',NULL,NULL,NULL,NULL,'2018-12-21 18:28:00.896','2018-12-21 18:28:00.896'),('130030','130013','130013',NULL,'taskId','string',0,NULL,NULL,NULL,'130022',NULL,'2018-12-21 18:29:11.750','2018-12-21 18:29:11.750'),('130031','130013','130013',NULL,'processInstanceId','string',0,NULL,NULL,NULL,'130013',NULL,'2018-12-21 18:29:11.751','2018-12-21 18:29:11.751'),('130032','130013','130013',NULL,'pass','string',0,NULL,NULL,NULL,'1',NULL,'2018-12-21 18:29:11.751','2018-12-21 18:29:11.751');

/*Table structure for table `act_id_group` */

DROP TABLE IF EXISTS `act_id_group`;

CREATE TABLE `act_id_group` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_group` */

/*Table structure for table `act_id_info` */

DROP TABLE IF EXISTS `act_id_info`;

CREATE TABLE `act_id_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD_` longblob,
  `PARENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_info` */

/*Table structure for table `act_id_membership` */

DROP TABLE IF EXISTS `act_id_membership`;

CREATE TABLE `act_id_membership` (
  `USER_ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `GROUP_ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`USER_ID_`,`GROUP_ID_`),
  KEY `ACT_FK_MEMB_GROUP` (`GROUP_ID_`),
  CONSTRAINT `ACT_FK_MEMB_GROUP` FOREIGN KEY (`GROUP_ID_`) REFERENCES `act_id_group` (`ID_`),
  CONSTRAINT `ACT_FK_MEMB_USER` FOREIGN KEY (`USER_ID_`) REFERENCES `act_id_user` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_membership` */

/*Table structure for table `act_id_user` */

DROP TABLE IF EXISTS `act_id_user`;

CREATE TABLE `act_id_user` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `FIRST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LAST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PWD_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PICTURE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_user` */

/*Table structure for table `act_procdef_info` */

DROP TABLE IF EXISTS `act_procdef_info`;

CREATE TABLE `act_procdef_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `INFO_JSON_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_IDX_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_INFO_JSON_BA` (`INFO_JSON_ID_`),
  CONSTRAINT `ACT_FK_INFO_JSON_BA` FOREIGN KEY (`INFO_JSON_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_INFO_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_procdef_info` */

/*Table structure for table `act_re_deployment` */

DROP TABLE IF EXISTS `act_re_deployment`;

CREATE TABLE `act_re_deployment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `DEPLOY_TIME_` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_re_deployment` */

insert  into `act_re_deployment`(`ID_`,`NAME_`,`CATEGORY_`,`TENANT_ID_`,`DEPLOY_TIME_`) values ('127545','请假',NULL,'','2018-12-21 18:15:21.668');

/*Table structure for table `act_re_model` */

DROP TABLE IF EXISTS `act_re_model`;

CREATE TABLE `act_re_model` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`),
  KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`),
  KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_MODEL_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE_EXTRA` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_re_model` */

insert  into `act_re_model`(`ID_`,`REV_`,`NAME_`,`KEY_`,`CATEGORY_`,`CREATE_TIME_`,`LAST_UPDATE_TIME_`,`VERSION_`,`META_INFO_`,`DEPLOYMENT_ID_`,`EDITOR_SOURCE_VALUE_ID_`,`EDITOR_SOURCE_EXTRA_VALUE_ID_`,`TENANT_ID_`) values ('112501',7,'请假','qingjia','005002','2018-12-21 15:52:19.392','2018-12-21 18:10:34.892',1,'{\"name\":\"请假\",\"revision\":1,\"description\":\"请假模型配置\"}',NULL,'112502','112503','');

/*Table structure for table `act_re_procdef` */

DROP TABLE IF EXISTS `act_re_procdef`;

CREATE TABLE `act_re_procdef` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HAS_START_FORM_KEY_` tinyint(4) DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_PROCDEF` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_re_procdef` */

insert  into `act_re_procdef`(`ID_`,`REV_`,`CATEGORY_`,`NAME_`,`KEY_`,`VERSION_`,`DEPLOYMENT_ID_`,`RESOURCE_NAME_`,`DGRM_RESOURCE_NAME_`,`DESCRIPTION_`,`HAS_START_FORM_KEY_`,`HAS_GRAPHICAL_NOTATION_`,`SUSPENSION_STATE_`,`TENANT_ID_`) values ('leave:1:127548',2,'005002','请假审批流程','leave',1,'127545','请假.bpmn20.xml','请假.leave.png','请假审批流程',0,1,1,'');

/*Table structure for table `act_ru_event_subscr` */

DROP TABLE IF EXISTS `act_ru_event_subscr`;

CREATE TABLE `act_ru_event_subscr` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CONFIGURATION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`),
  CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_event_subscr` */

/*Table structure for table `act_ru_execution` */

DROP TABLE IF EXISTS `act_ru_execution`;

CREATE TABLE `act_ru_execution` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_EXEC_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IS_ACTIVE_` tinyint(4) DEFAULT NULL,
  `IS_CONCURRENT_` tinyint(4) DEFAULT NULL,
  `IS_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_EVENT_SCOPE_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `CACHED_ENT_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`),
  KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`),
  KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`),
  KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_execution` */

insert  into `act_ru_execution`(`ID_`,`REV_`,`PROC_INST_ID_`,`BUSINESS_KEY_`,`PARENT_ID_`,`PROC_DEF_ID_`,`SUPER_EXEC_`,`ACT_ID_`,`IS_ACTIVE_`,`IS_CONCURRENT_`,`IS_SCOPE_`,`IS_EVENT_SCOPE_`,`SUSPENSION_STATE_`,`CACHED_ENT_STATE_`,`TENANT_ID_`,`NAME_`,`LOCK_TIME_`) values ('130001',1,'130001','leave',NULL,'leave:1:127548',NULL,'task1',1,0,1,0,1,2,'',NULL,NULL),('130013',2,'130013','leave',NULL,'leave:1:127548',NULL,'task2',1,0,1,0,1,2,'',NULL,NULL);

/*Table structure for table `act_ru_identitylink` */

DROP TABLE IF EXISTS `act_ru_identitylink`;

CREATE TABLE `act_ru_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`),
  KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`),
  KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID_`) REFERENCES `act_ru_task` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_identitylink` */

insert  into `act_ru_identitylink`(`ID_`,`REV_`,`GROUP_ID_`,`TYPE_`,`USER_ID_`,`TASK_ID_`,`PROC_INST_ID_`,`PROC_DEF_ID_`) values ('130002',1,NULL,'starter','1',NULL,'130001',NULL),('130011',1,NULL,'candidate','c9e3202c3f9a4b5da6d4c8aede8ac807','130010',NULL,NULL),('130012',1,NULL,'participant','c9e3202c3f9a4b5da6d4c8aede8ac807',NULL,'130001',NULL),('130014',1,NULL,'starter','1',NULL,'130013',NULL),('130024',1,NULL,'participant','0ee035ff27f540f78a3c8ec515a22db1',NULL,'130013',NULL),('130026',1,NULL,'participant','2',NULL,'130013',NULL),('130028',1,NULL,'participant','c9e3202c3f9a4b5da6d4c8aede8ac807',NULL,'130013',NULL);

/*Table structure for table `act_ru_job` */

DROP TABLE IF EXISTS `act_ru_job`;

CREATE TABLE `act_ru_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_job` */

/*Table structure for table `act_ru_task` */

DROP TABLE IF EXISTS `act_ru_task`;

CREATE TABLE `act_ru_task` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DELEGATION_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`),
  KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_task` */

insert  into `act_ru_task`(`ID_`,`REV_`,`EXECUTION_ID_`,`PROC_INST_ID_`,`PROC_DEF_ID_`,`NAME_`,`PARENT_TASK_ID_`,`DESCRIPTION_`,`TASK_DEF_KEY_`,`OWNER_`,`ASSIGNEE_`,`DELEGATION_`,`PRIORITY_`,`CREATE_TIME_`,`DUE_DATE_`,`CATEGORY_`,`SUSPENSION_STATE_`,`TENANT_ID_`,`FORM_KEY_`) values ('130010',1,'130001','130001','leave:1:127548','初步审核',NULL,'请假的初步审核，测试变量设置流程处理人','task1',NULL,NULL,NULL,50,'2018-12-21 18:26:24.202',NULL,NULL,1,'',NULL),('130034',1,'130013','130013','leave:1:127548','第二步审核',NULL,'测试流程变量的互相覆盖问题','task2',NULL,'2',NULL,50,'2018-12-21 18:29:11.762',NULL,NULL,1,'',NULL);

/*Table structure for table `act_ru_variable` */

DROP TABLE IF EXISTS `act_ru_variable`;

CREATE TABLE `act_ru_variable` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`),
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`),
  CONSTRAINT `ACT_FK_VAR_BYTEARRAY` FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_variable` */

insert  into `act_ru_variable`(`ID_`,`REV_`,`TYPE_`,`NAME_`,`EXECUTION_ID_`,`PROC_INST_ID_`,`TASK_ID_`,`BYTEARRAY_ID_`,`DOUBLE_`,`LONG_`,`TEXT_`,`TEXT2_`) values ('130004',1,'integer','days','130001','130001',NULL,NULL,NULL,2,'2',NULL),('130005',1,'string','inputUser','130001','130001',NULL,NULL,NULL,NULL,'2',NULL),('130007',1,'serializable','roles','130001','130001',NULL,'130006',NULL,NULL,NULL,NULL),('130016',1,'integer','days','130013','130013',NULL,NULL,NULL,2,'2',NULL),('130017',2,'string','inputUser','130013','130013',NULL,NULL,NULL,NULL,'0ee035ff27f540f78a3c8ec515a22db1',NULL),('130019',1,'serializable','roles','130013','130013',NULL,'130018',NULL,NULL,NULL,NULL),('130030',1,'string','taskId','130013','130013',NULL,NULL,NULL,NULL,'130022',NULL),('130031',1,'string','processInstanceId','130013','130013',NULL,NULL,NULL,NULL,'130013',NULL),('130032',1,'string','pass','130013','130013',NULL,NULL,NULL,NULL,'1',NULL);

/*Table structure for table `tab_attend_date` */

DROP TABLE IF EXISTS `tab_attend_date`;

CREATE TABLE `tab_attend_date` (
  `id` varchar(255) NOT NULL COMMENT '考勤日期id',
  `calendar_date` date DEFAULT NULL COMMENT '所有日期',
  `is_workingday` varchar(255) DEFAULT NULL COMMENT '工作日(1.是-0.否)',
  `delete_flag` varchar(255) DEFAULT '1' COMMENT '删除(0.删除-1.正常)',
  `mark` varchar(1024) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_attend_date` */

insert  into `tab_attend_date`(`id`,`calendar_date`,`is_workingday`,`delete_flag`,`mark`) values ('1','2018-01-01','1','1','新年新气象~加油ヾ(◍°∇°◍)ﾉﾞ~！！！'),('10','2018-01-10','1','1',NULL),('100','2018-04-10','1','1',NULL),('101','2018-04-11','1','1',NULL),('102','2018-04-12','1','1',NULL),('103','2018-04-13','1','1',NULL),('104','2018-04-14','0','1',NULL),('105','2018-04-15','0','1',NULL),('106','2018-04-16','1','1',NULL),('107','2018-04-17','1','1',NULL),('108','2018-04-18','1','1',NULL),('109','2018-04-19','1','1',NULL),('11','2018-01-11','1','1',NULL),('110','2018-04-20','1','1',NULL),('111','2018-04-21','0','1',NULL),('112','2018-04-22','0','1',NULL),('113','2018-04-23','1','1',NULL),('114','2018-04-24','1','1',NULL),('115','2018-04-25','1','1',NULL),('116','2018-04-26','1','1',NULL),('117','2018-04-27','1','1',NULL),('118','2018-04-28','0','1',NULL),('119','2018-04-29','0','1',NULL),('12','2018-01-12','1','1',NULL),('120','2018-04-30','1','1',NULL),('121','2018-05-01','1','1',NULL),('122','2018-05-02','1','1',NULL),('123','2018-05-03','1','1',NULL),('124','2018-05-04','1','1',NULL),('125','2018-05-05','0','1',NULL),('126','2018-05-06','0','1',NULL),('127','2018-05-07','1','1',NULL),('128','2018-05-08','1','1',NULL),('129','2018-05-09','1','1',NULL),('13','2018-01-13','0','1',NULL),('130','2018-05-10','1','1',NULL),('131','2018-05-11','1','1',NULL),('132','2018-05-12','0','1',NULL),('133','2018-05-13','0','1',NULL),('134','2018-05-14','1','1',NULL),('135','2018-05-15','1','1',NULL),('136','2018-05-16','1','1',NULL),('137','2018-05-17','1','1',NULL),('138','2018-05-18','1','1',NULL),('139','2018-05-19','0','1',NULL),('14','2018-01-14','0','1',NULL),('140','2018-05-20','0','1',NULL),('141','2018-05-21','1','1',NULL),('142','2018-05-22','1','1',NULL),('143','2018-05-23','1','1',NULL),('144','2018-05-24','1','1',NULL),('145','2018-05-25','1','1',NULL),('146','2018-05-26','0','1',NULL),('147','2018-05-27','0','1',NULL),('148','2018-05-28','1','1',NULL),('149','2018-05-29','1','1',NULL),('15','2018-01-15','1','1',NULL),('150','2018-05-30','1','1',NULL),('151','2018-05-31','1','1',NULL),('152','2018-06-01','1','1',NULL),('153','2018-06-02','0','1',NULL),('154','2018-06-03','0','1',NULL),('155','2018-06-04','1','1',NULL),('156','2018-06-05','1','1',NULL),('157','2018-06-06','1','1',NULL),('158','2018-06-07','1','1',NULL),('159','2018-06-08','1','1',NULL),('16','2018-01-16','1','1',NULL),('160','2018-06-09','0','1',NULL),('161','2018-06-10','0','1',NULL),('162','2018-06-11','1','1',NULL),('163','2018-06-12','1','1',NULL),('164','2018-06-13','1','1',NULL),('165','2018-06-14','1','1',NULL),('166','2018-06-15','1','1',NULL),('167','2018-06-16','0','1',NULL),('168','2018-06-17','0','1',NULL),('169','2018-06-18','1','1',NULL),('17','2018-01-17','1','1',NULL),('170','2018-06-19','1','1',NULL),('171','2018-06-20','1','1',NULL),('172','2018-06-21','1','1',NULL),('173','2018-06-22','1','1','测试一下'),('174','2018-06-23','0','1',NULL),('175','2018-06-24','0','1',NULL),('176','2018-06-25','1','1',NULL),('177','2018-06-26','1','1',NULL),('178','2018-06-27','1','1',NULL),('179','2018-06-28','1','1',NULL),('18','2018-01-18','1','1',NULL),('180','2018-06-29','1','1',NULL),('181','2018-06-30','0','1',NULL),('182','2018-07-01','0','1',NULL),('183','2018-07-02','1','1',NULL),('184','2018-07-03','1','1',NULL),('185','2018-07-04','1','1',NULL),('186','2018-07-05','1','1',NULL),('187','2018-07-06','1','1',NULL),('188','2018-07-07','0','1',NULL),('189','2018-07-08','0','1',NULL),('19','2018-01-19','1','1',NULL),('190','2018-07-09','1','1',NULL),('191','2018-07-10','1','1',NULL),('192','2018-07-11','1','1',NULL),('193','2018-07-12','1','1',NULL),('194','2018-07-13','1','1',NULL),('195','2018-07-14','0','1',NULL),('196','2018-07-15','0','1',NULL),('197','2018-07-16','1','1',NULL),('198','2018-07-17','1','1',NULL),('199','2018-07-18','1','1',NULL),('2','2018-01-02','1','1','第二天也要努力哦~不要轻易泄气！！！'),('20','2018-01-20','0','1',NULL),('200','2018-07-19','1','1',NULL),('201','2018-07-20','1','1',NULL),('202','2018-07-21','0','1',NULL),('203','2018-07-22','0','1',NULL),('204','2018-07-23','1','1',NULL),('205','2018-07-24','1','1',NULL),('206','2018-07-25','1','1',NULL),('207','2018-07-26','1','1',NULL),('208','2018-07-27','1','1',NULL),('209','2018-07-28','0','1',NULL),('21','2018-01-21','0','1',NULL),('210','2018-07-29','0','1',NULL),('211','2018-07-30','1','1',NULL),('212','2018-07-31','1','1',NULL),('213','2018-08-01','1','1',NULL),('214','2018-08-02','1','1',NULL),('215','2018-08-03','1','1',NULL),('216','2018-08-04','0','1',NULL),('217','2018-08-05','0','1',NULL),('218','2018-08-06','1','1',NULL),('219','2018-08-07','1','1',NULL),('22','2018-01-22','1','1',NULL),('220','2018-08-08','1','1',NULL),('221','2018-08-09','1','1',NULL),('222','2018-08-10','1','1',NULL),('223','2018-08-11','0','1',NULL),('224','2018-08-12','0','1',NULL),('225','2018-08-13','1','1',NULL),('226','2018-08-14','1','1',NULL),('227','2018-08-15','1','1',NULL),('228','2018-08-16','1','1',NULL),('229','2018-08-17','1','1',NULL),('23','2018-01-23','1','1',NULL),('230','2018-08-18','0','1',NULL),('231','2018-08-19','0','1',NULL),('232','2018-08-20','1','1',NULL),('233','2018-08-21','1','1',NULL),('234','2018-08-22','1','1',NULL),('235','2018-08-23','1','1',NULL),('236','2018-08-24','1','1',NULL),('237','2018-08-25','0','1',NULL),('238','2018-08-26','0','1',NULL),('239','2018-08-27','1','1',NULL),('24','2018-01-24','1','1',NULL),('240','2018-08-28','1','1',NULL),('241','2018-08-29','1','1',NULL),('242','2018-08-30','1','1',NULL),('243','2018-08-31','1','1',NULL),('244','2018-09-01','0','1',NULL),('245','2018-09-02','0','1',NULL),('246','2018-09-03','1','1',NULL),('247','2018-09-04','1','1',NULL),('248','2018-09-05','1','1',NULL),('249','2018-09-06','1','1',NULL),('25','2018-01-25','1','1',NULL),('250','2018-09-07','1','1',NULL),('251','2018-09-08','0','1',NULL),('252','2018-09-09','0','1',NULL),('253','2018-09-10','1','1',NULL),('254','2018-09-11','1','1',NULL),('255','2018-09-12','1','1',NULL),('256','2018-09-13','1','1',NULL),('257','2018-09-14','1','1',NULL),('258','2018-09-15','0','1',NULL),('259','2018-09-16','0','1',NULL),('26','2018-01-26','1','1',NULL),('260','2018-09-17','1','1',NULL),('261','2018-09-18','1','1',NULL),('262','2018-09-19','1','1',NULL),('263','2018-09-20','1','1',NULL),('264','2018-09-21','1','1',NULL),('265','2018-09-22','0','1',NULL),('266','2018-09-23','0','1',NULL),('267','2018-09-24','1','1',NULL),('268','2018-09-25','1','1',NULL),('269','2018-09-26','1','1',NULL),('27','2018-01-27','0','1',NULL),('270','2018-09-27','1','1',NULL),('271','2018-09-28','1','1',NULL),('272','2018-09-29','0','1',NULL),('273','2018-09-30','0','1',NULL),('274','2018-10-01','1','1',NULL),('275','2018-10-02','1','1',NULL),('276','2018-10-03','1','1',NULL),('277','2018-10-04','1','1',NULL),('278','2018-10-05','1','1',NULL),('279','2018-10-06','0','1',NULL),('28','2018-01-28','0','1',NULL),('280','2018-10-07','0','1',NULL),('281','2018-10-08','1','1',NULL),('282','2018-10-09','1','1',NULL),('283','2018-10-10','1','1',NULL),('284','2018-10-11','1','1',NULL),('285','2018-10-12','1','1',NULL),('286','2018-10-13','0','1',NULL),('287','2018-10-14','0','1',NULL),('288','2018-10-15','1','1',NULL),('289','2018-10-16','1','1',NULL),('29','2018-01-29','1','1',NULL),('290','2018-10-17','1','1',NULL),('291','2018-10-18','1','1',NULL),('292','2018-10-19','1','1',NULL),('293','2018-10-20','0','1',NULL),('294','2018-10-21','0','1',NULL),('295','2018-10-22','1','1',NULL),('296','2018-10-23','1','1',NULL),('297','2018-10-24','1','1',NULL),('298','2018-10-25','1','1',NULL),('299','2018-10-26','1','1',NULL),('3','2018-01-03','1','1',''),('30','2018-01-30','1','1',NULL),('300','2018-10-27','0','1',NULL),('301','2018-10-28','0','1',NULL),('302','2018-10-29','1','1',NULL),('303','2018-10-30','1','1',NULL),('304','2018-10-31','1','1',NULL),('305','2018-11-01','1','1',NULL),('306','2018-11-02','1','1',NULL),('307','2018-11-03','0','1',NULL),('308','2018-11-04','0','1',NULL),('309','2018-11-05','1','1',NULL),('31','2018-01-31','1','1',NULL),('310','2018-11-06','1','1',NULL),('311','2018-11-07','1','1',NULL),('312','2018-11-08','1','1',NULL),('313','2018-11-09','1','1',NULL),('314','2018-11-10','0','1',NULL),('315','2018-11-11','0','1',NULL),('316','2018-11-12','1','1',NULL),('317','2018-11-13','1','1',NULL),('318','2018-11-14','1','1',NULL),('319','2018-11-15','1','1',NULL),('32','2018-02-01','1','1',NULL),('320','2018-11-16','1','1',NULL),('321','2018-11-17','0','1',NULL),('322','2018-11-18','0','1',NULL),('323','2018-11-19','1','1',NULL),('324','2018-11-20','1','1',NULL),('325','2018-11-21','1','1',NULL),('326','2018-11-22','1','1',NULL),('327','2018-11-23','1','1',NULL),('328','2018-11-24','0','1',NULL),('329','2018-11-25','0','1',NULL),('33','2018-02-02','1','1',NULL),('330','2018-11-26','1','1',NULL),('331','2018-11-27','1','1',NULL),('332','2018-11-28','1','1',NULL),('333','2018-11-29','1','1',NULL),('334','2018-11-30','1','1',NULL),('335','2018-12-01','0','1',NULL),('336','2018-12-02','0','1',NULL),('337','2018-12-03','1','1',NULL),('338','2018-12-04','1','1',NULL),('339','2018-12-05','1','1',NULL),('34','2018-02-03','0','1',NULL),('340','2018-12-06','1','1',NULL),('341','2018-12-07','1','1',NULL),('342','2018-12-08','0','1',NULL),('343','2018-12-09','0','1',NULL),('344','2018-12-10','1','1',NULL),('345','2018-12-11','1','1',NULL),('346','2018-12-12','1','1',NULL),('347','2018-12-13','1','1',NULL),('348','2018-12-14','1','1',NULL),('349','2018-12-15','0','1',NULL),('35','2018-02-04','0','1',NULL),('350','2018-12-16','0','1',NULL),('351','2018-12-17','1','1',NULL),('352','2018-12-18','1','1',NULL),('353','2018-12-19','1','1',NULL),('354','2018-12-20','1','1',NULL),('355','2018-12-21','1','1',NULL),('356','2018-12-22','0','1',NULL),('357','2018-12-23','0','1',NULL),('358','2018-12-24','1','1',NULL),('359','2018-12-25','1','1',NULL),('36','2018-02-05','1','1',NULL),('360','2018-12-26','1','1',NULL),('361','2018-12-27','1','1',NULL),('362','2018-12-28','1','1',NULL),('363','2018-12-29','0','1',NULL),('364','2018-12-30','0','1',NULL),('365','2018-12-31','1','1',NULL),('37','2018-02-06','1','1',NULL),('38','2018-02-07','1','1',NULL),('39','2018-02-08','1','1',NULL),('4','2018-01-04','1','1',NULL),('40','2018-02-09','1','1',NULL),('41','2018-02-10','0','1',NULL),('42','2018-02-11','0','1',NULL),('43','2018-02-12','1','1',NULL),('44','2018-02-13','1','1',NULL),('45','2018-02-14','1','1',NULL),('46','2018-02-15','1','1',NULL),('47','2018-02-16','1','1',NULL),('48','2018-02-17','0','1',NULL),('49','2018-02-18','0','1',NULL),('5','2018-01-05','1','1',NULL),('50','2018-02-19','1','1',NULL),('51','2018-02-20','1','1',NULL),('52','2018-02-21','1','1',NULL),('53','2018-02-22','1','1',NULL),('54','2018-02-23','1','1',NULL),('55','2018-02-24','0','1',NULL),('56','2018-02-25','0','1',NULL),('57','2018-02-26','1','1',NULL),('58','2018-02-27','1','1',NULL),('59','2018-02-28','1','1',NULL),('6','2018-01-06','0','1',NULL),('60','2018-03-01','1','1',NULL),('61','2018-03-02','1','1',NULL),('62','2018-03-03','0','1',NULL),('63','2018-03-04','0','1',NULL),('64','2018-03-05','1','1',NULL),('65','2018-03-06','1','1',NULL),('66','2018-03-07','1','1',NULL),('67','2018-03-08','1','1',NULL),('68','2018-03-09','1','1',NULL),('69','2018-03-10','0','1',NULL),('7','2018-01-07','0','1',NULL),('70','2018-03-11','0','1',NULL),('71','2018-03-12','1','1',NULL),('72','2018-03-13','1','1',NULL),('73','2018-03-14','1','1',NULL),('74','2018-03-15','1','1',NULL),('75','2018-03-16','1','1',NULL),('76','2018-03-17','0','1',NULL),('77','2018-03-18','0','1',NULL),('78','2018-03-19','1','1',NULL),('79','2018-03-20','1','1',NULL),('8','2018-01-08','1','1',NULL),('80','2018-03-21','1','1',NULL),('81','2018-03-22','1','1',NULL),('82','2018-03-23','1','1',NULL),('83','2018-03-24','0','1',NULL),('84','2018-03-25','0','1',NULL),('85','2018-03-26','1','1',NULL),('86','2018-03-27','1','1',NULL),('87','2018-03-28','1','1',NULL),('88','2018-03-29','1','1',NULL),('89','2018-03-30','1','1',NULL),('9','2018-01-09','1','1',NULL),('90','2018-03-31','0','1',NULL),('91','2018-04-01','0','1',NULL),('92','2018-04-02','1','1',NULL),('93','2018-04-03','1','1',NULL),('94','2018-04-04','1','1',NULL),('95','2018-04-05','1','1',NULL),('96','2018-04-06','1','1',NULL),('97','2018-04-07','0','1',NULL),('98','2018-04-08','0','1',NULL),('99','2018-04-09','1','1',NULL);

/*Table structure for table `tab_attend_info` */

DROP TABLE IF EXISTS `tab_attend_info`;

CREATE TABLE `tab_attend_info` (
  `id` varchar(255) NOT NULL COMMENT '考勤记录id',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `work_date` date DEFAULT NULL COMMENT '工作日',
  `on_duty_time` time DEFAULT NULL COMMENT '上班考勤时间',
  `off_duty_time` time DEFAULT NULL COMMENT '下班考勤时间',
  `on_status` varchar(255) DEFAULT NULL COMMENT '上班状态(0.迟-1.正)',
  `off_status` varchar(255) DEFAULT NULL COMMENT '下班状态(0.早-1.正)',
  `late_mark` varchar(255) DEFAULT NULL COMMENT '迟到原因',
  `early_mark` varchar(255) DEFAULT NULL COMMENT '早退原因',
  `delete_flag` varchar(255) DEFAULT '1' COMMENT '删除(0.删除-1.正常)',
  `mark` varchar(1024) DEFAULT NULL COMMENT '缺勤说明',
  `late_time` varchar(255) DEFAULT NULL COMMENT '迟到时间',
  `early_time` varchar(255) DEFAULT NULL COMMENT '早退时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_attend_info` */

/*Table structure for table `tab_attend_time` */

DROP TABLE IF EXISTS `tab_attend_time`;

CREATE TABLE `tab_attend_time` (
  `id` varchar(255) NOT NULL COMMENT '考勤时间id',
  `on_time` time DEFAULT NULL COMMENT '上班时间',
  `off_time` time DEFAULT NULL COMMENT '下班时间',
  `delete_flag` varchar(255) DEFAULT '1' COMMENT '删除(1.正常-0.删除)',
  `mark` varchar(1024) DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_attend_time` */

insert  into `tab_attend_time`(`id`,`on_time`,`off_time`,`delete_flag`,`mark`) values ('5cb13477dbbc4f158f0436e46dd83b31','09:30:00','18:30:00','1','OK');

/*Table structure for table `tab_fastdfs_document` */

DROP TABLE IF EXISTS `tab_fastdfs_document`;

CREATE TABLE `tab_fastdfs_document` (
  `id` varchar(50) NOT NULL COMMENT '主键id',
  `name` varchar(50) DEFAULT NULL COMMENT '文件名称',
  `mark` varchar(400) DEFAULT NULL COMMENT '文件描述',
  `group_name` varchar(50) DEFAULT NULL COMMENT '存储文件节点名称',
  `file_path` varchar(400) DEFAULT NULL COMMENT '文件路径',
  `file_type` varchar(50) DEFAULT NULL COMMENT '文件类型',
  `file_size` varchar(50) DEFAULT NULL COMMENT '文件大小',
  `type` varchar(10) DEFAULT '0' COMMENT '是否是下载空间内的文件 0不是，1是',
  `file_class` varchar(50) DEFAULT NULL COMMENT '文件分类',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_id` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_id` varchar(50) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_fastdfs_document` */

insert  into `tab_fastdfs_document`(`id`,`name`,`mark`,`group_name`,`file_path`,`file_type`,`file_size`,`type`,`file_class`,`create_time`,`update_time`,`create_id`,`update_id`) values ('0694941a203a46d3a3e42ffbe6b0890b','桌面1.jpg',NULL,'group1','M00/00/00/wKgZhVt6JOuAWDeYAAKGHc6jVsA560.jpg','jpg','165405','0',NULL,'2018-08-20 10:18:19',NULL,'1',NULL),('17c2804009e34b4faa9002dbb1e46aa9','111.jpg',NULL,'group1','M00/00/00/wKgZhVt6IsiAJp8ZAAITd58BE7Y986.jpg','jpg','136055','0',NULL,'2018-08-20 10:09:13',NULL,'1',NULL),('1f6f02fb0a89493b96b6c43c29952679','桌面2.jpg',NULL,'group1','M00/00/00/wKgZhVt6K5aAc4JsAAJRPAauR8Q947.jpg','jpg','151868','0',NULL,'2018-08-20 10:46:47',NULL,'1',NULL),('23bcc80374fc4883a8a10df4fc4fc6df','桌面3.jpg',NULL,'group1','M00/00/00/wKgZhVt6Km-Af56zAAE7wTpK2e0227.jpg','jpg','80833','0',NULL,'2018-08-20 10:41:51',NULL,'1',NULL),('29cd37f4be70486b804775d487984f04','桌面3.jpg',NULL,'group1','M00/00/00/wKgZhVt6fy2AAdBhAAE7wTpK2e0282.jpg','jpg','80833','0',NULL,'2018-08-20 16:43:26',NULL,'1',NULL),('35a4b0664e2746008f14a4c904cc7b06','桌面2.jpg',NULL,'group1','M00/00/00/wKgZhVt6K_CAW2c4AAJRPAauR8Q293.jpg','jpg','151868','0',NULL,'2018-08-20 10:48:17',NULL,'1',NULL),('a1afc43a292f43ff998d1c3b15b5e20b','111.jpg',NULL,'group1','M00/00/00/wKgZhVt729iAMMC6AAITd58BE7Y878.jpg','jpg','136055','0',NULL,'2018-08-21 17:31:05',NULL,'1',NULL),('e15210900c714818a49b8dee1acab1f2','桌面1.jpg',NULL,'group1','M00/00/00/wKgZhVt6fyWAVK8eAAKGHc6jVsA179.jpg','jpg','165405','0',NULL,'2018-08-20 16:43:18',NULL,'1',NULL);

/*Table structure for table `tab_per_myinfo` */

DROP TABLE IF EXISTS `tab_per_myinfo`;

CREATE TABLE `tab_per_myinfo` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `person_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `person_header` varchar(100) DEFAULT NULL COMMENT '头像',
  `person_sign` varchar(500) DEFAULT NULL COMMENT '个性签名',
  `person_sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `person_address` varchar(100) DEFAULT NULL COMMENT '地址',
  `person_tel` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `person_email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `person_num` varchar(50) DEFAULT NULL COMMENT '个人编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_per_myinfo` */

insert  into `tab_per_myinfo`(`id`,`person_name`,`person_header`,`person_sign`,`person_sex`,`person_address`,`person_tel`,`person_email`,`person_num`) values ('1','棒棒糖','M00/00/00/wKgZhVt729iAMMC6AAITd58BE7Y878.jpg','棒棒糖棒棒糖棒棒糖棒棒糖','0','棒棒糖棒棒糖棒棒糖','12345678912','123@qq.com','201808191932210326');

/*Table structure for table `tab_sys_dept` */

DROP TABLE IF EXISTS `tab_sys_dept`;

CREATE TABLE `tab_sys_dept` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `parent_id` varchar(50) DEFAULT NULL COMMENT '上级部门id',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(10) DEFAULT NULL COMMENT '排序号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_id` varchar(50) DEFAULT NULL COMMENT '创建人id',
  `update_id` varchar(50) DEFAULT NULL COMMENT '修改人id',
  `delete_flag` varchar(10) DEFAULT NULL COMMENT '删除标记(0.删除--1.正常)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_sys_dept` */

insert  into `tab_sys_dept`(`id`,`parent_id`,`dept_name`,`order_num`,`create_time`,`update_time`,`create_id`,`update_id`,`delete_flag`) values ('1','0','中国区',1,'2018-07-08 15:00:14',NULL,'1',NULL,'1'),('10','4','外包部',2,'2018-08-03 15:05:13',NULL,'1',NULL,'1'),('2','1','北京分公司',1,'2018-08-01 15:01:01',NULL,'1',NULL,'1'),('3','1','西安分公司',2,'2018-08-02 15:01:30',NULL,'1',NULL,'1'),('4','1','上海分公司',3,'2018-08-03 15:01:58',NULL,'1',NULL,'1'),('5','2','开发部',1,'2018-08-03 15:02:33',NULL,'1',NULL,'1'),('6','2','测试部',2,'2018-08-03 15:02:52',NULL,'1',NULL,'1'),('7','3','研发部',1,'2018-08-03 15:03:17',NULL,'1',NULL,'1'),('8','3','销售部',2,'2018-08-03 15:03:37',NULL,'1',NULL,'1'),('9','4','企划部',1,'2018-08-03 15:04:21',NULL,'1',NULL,'1');

/*Table structure for table `tab_sys_dictionary` */

DROP TABLE IF EXISTS `tab_sys_dictionary`;

CREATE TABLE `tab_sys_dictionary` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `code` varchar(50) DEFAULT NULL COMMENT '编码',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `sorting` int(50) DEFAULT NULL COMMENT '排序号',
  `parent_id` varchar(50) DEFAULT NULL COMMENT '父Id',
  `params` varchar(500) DEFAULT NULL COMMENT '参数',
  `value` varchar(500) DEFAULT NULL COMMENT '值',
  `remark` varchar(500) DEFAULT NULL COMMENT '描述',
  `edit_state` int(50) DEFAULT NULL COMMENT '编辑状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_id` varchar(50) DEFAULT NULL COMMENT '创建人id',
  `update_id` varchar(50) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_sys_dictionary` */

insert  into `tab_sys_dictionary`(`id`,`code`,`name`,`sorting`,`parent_id`,`params`,`value`,`remark`,`edit_state`,`create_time`,`update_time`,`create_id`,`update_id`) values ('0916e456e80e439eae99e86aab3da55f','004007','硕士',NULL,'50c5fbcd741247e7b73017d2ec5b8ad9','硕士','004007','硕士',1,'2018-08-20 16:04:14',NULL,'1',NULL),('0ea9102d81de444d8ea494dbad3ea301','002002','良好',NULL,'c00e99076ad640679d4bd7fdb4b9104c','良好','002002','良好',1,'2018-08-20 16:02:26',NULL,'1',NULL),('101d74aa78aa4afeb65ab62fe17f422b','005002','测试类',NULL,'2e1c25213eaf404bad9455561aea7a83','测试类','005002','测试类',2,'2018-12-06 09:50:45','2018-12-21 10:25:53','1','1'),('2c87be9363aa4fc7a5d8c9b05aaf8567','004005','大专',NULL,'50c5fbcd741247e7b73017d2ec5b8ad9','大专','004005','大专',1,'2018-08-20 16:03:55',NULL,'1',NULL),('2e1c25213eaf404bad9455561aea7a83','005','流程分类',NULL,NULL,'流程分类','005','流程分类',1,'2018-12-06 09:50:16',NULL,'1',NULL),('2fd80af9ee2349dc9e543160fe3f29a7','004006','本科',NULL,'50c5fbcd741247e7b73017d2ec5b8ad9','本科','004006','本科',1,'2018-08-20 16:04:03',NULL,'1',NULL),('4d3a53be67f84d5a845ce08dc8272a3b','002003','欠佳',NULL,'c00e99076ad640679d4bd7fdb4b9104c','欠佳','002003','欠佳',1,'2018-08-20 16:02:38',NULL,'1',NULL),('501642d1d6b64bd1b607d90efbe2354f','004003','高中',NULL,'50c5fbcd741247e7b73017d2ec5b8ad9','高中','004003','高中',1,'2018-08-20 16:03:35',NULL,'1',NULL),('50c5fbcd741247e7b73017d2ec5b8ad9','004','学位',NULL,NULL,'学位','004','学位',1,'2018-08-20 16:01:10',NULL,'1',NULL),('514293b5f546492ebae3ffb8c785b6a7','001002','图片',NULL,'be2bfb2ac03f49e49b64bae174046914','图片','001002','图片',1,'2018-08-20 16:01:44',NULL,'1',NULL),('554087c5a82a4c79b427b1268a2ce515','002004','残疾',NULL,'c00e99076ad640679d4bd7fdb4b9104c','残疾','002004','残疾',1,'2018-08-20 16:02:47',NULL,'1',NULL),('57a41818cd2b403c9f4fc7f699d8c8bd','006','流程变量',NULL,'','流程人员','006','流程人员变量配置',2,'2018-12-21 10:26:25','2018-12-21 17:42:50','1','1'),('58d351328cf743f6ab11ca05bb9adc1e','003001','男',NULL,'ddab02025f2d4992a59e70fd2ba74751','男','003001','男',1,'2018-08-20 16:02:57',NULL,'1',NULL),('69aa0161fffd47059646b3fcf60e8bc7','005001','常用类',NULL,'2e1c25213eaf404bad9455561aea7a83','常用类','005001','常用类',2,'2018-12-06 09:50:32','2018-12-21 10:25:42','1','1'),('7c681d4ef92246bdab6cbd0969ef347e','006001','单人',NULL,'57a41818cd2b403c9f4fc7f699d8c8bd','assignee','006001','单人表示指定某一个特定处理人-默认单人变量分配给周启c9e3202c3f9a4b5da6d4c8aede8ac807',2,'2018-12-21 10:26:41','2018-12-21 17:47:50','1','1'),('8c15f52b411b4d0a822073ad3c3739ff','006002','角色',NULL,'57a41818cd2b403c9f4fc7f699d8c8bd','roles','006002','根据角色分配多个候选人--默认分配给测试用户角色\n4e9d699a85644ac98e2533a910e4594a',2,'2018-12-21 10:26:56','2018-12-21 18:12:37','1','1'),('a4689b34d7614cb9926fcb7962a06d03','004001','小学',NULL,'50c5fbcd741247e7b73017d2ec5b8ad9','小学','004001','小学',2,'2018-08-20 16:03:16','2018-08-20 16:04:40','1','1'),('b4afd409698f4ee1b918fbda463c0f70','002001','健康',NULL,'c00e99076ad640679d4bd7fdb4b9104c','健康','002001','健康',1,'2018-08-20 16:01:55',NULL,'1',NULL),('b61cec37b69847649457e1c28e1ef3e5','001001','头像',NULL,'be2bfb2ac03f49e49b64bae174046914','头像','001001','头像',1,'2018-08-20 16:01:31',NULL,'1',NULL),('be2bfb2ac03f49e49b64bae174046914','001','文件类型',NULL,NULL,'文件类型','001','文件类型',1,'2018-08-20 15:58:00',NULL,'1',NULL),('c00e99076ad640679d4bd7fdb4b9104c','002','健康情况',NULL,NULL,'健康情况','002','健康情况',1,'2018-08-20 16:00:36',NULL,'1',NULL),('d3d367e530c24792b2b587d2b257f85a','003002','女',NULL,'ddab02025f2d4992a59e70fd2ba74751','女','003002','女',1,'2018-08-20 16:03:05',NULL,'1',NULL),('dbb13ecc66934f07ae0810365336faac','004004','中专',NULL,'50c5fbcd741247e7b73017d2ec5b8ad9','中专','004004','中专',1,'2018-08-20 16:03:45',NULL,'1',NULL),('dbfed9a214a74079960373ec71d570e6','004008','博士',NULL,'50c5fbcd741247e7b73017d2ec5b8ad9','博士','004008','博士',1,'2018-08-20 16:04:24',NULL,'1',NULL),('ddab02025f2d4992a59e70fd2ba74751','003','性别',NULL,NULL,'性别','003','性别',1,'2018-08-20 16:00:53',NULL,'1',NULL),('f052c4bf238e4a1087c7c3132c72ddd4','004002','初中',NULL,'50c5fbcd741247e7b73017d2ec5b8ad9','初中','004002','初中',1,'2018-08-20 16:03:25',NULL,'1',NULL);

/*Table structure for table `tab_sys_menu` */

DROP TABLE IF EXISTS `tab_sys_menu`;

CREATE TABLE `tab_sys_menu` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `parent_id` varchar(50) DEFAULT NULL COMMENT '父级id',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(255) DEFAULT NULL COMMENT '菜单url',
  `perms` varchar(255) DEFAULT NULL COMMENT '授权标识',
  `type` int(1) DEFAULT NULL COMMENT '菜单类型0.目录--1.菜单--2.按钮',
  `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(3) DEFAULT NULL COMMENT '排序号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_id` varchar(50) DEFAULT NULL COMMENT '创建人id',
  `update_id` varchar(50) DEFAULT NULL COMMENT '修改人id',
  `delete_flag` varchar(1) DEFAULT NULL COMMENT '删除标记(0.删除--1.正常)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_sys_menu` */

insert  into `tab_sys_menu`(`id`,`parent_id`,`menu_name`,`url`,`perms`,`type`,`icon`,`order_num`,`create_time`,`update_time`,`create_id`,`update_id`,`delete_flag`) values ('03f3dcc0af964f5ca191114a0d946250','a564f8a75e77469e97845139078a46cb','已办任务','work/task/connectionlistpage','',1,'',2,'2018-12-10 15:56:45','2018-12-18 10:10:22','1','1','1'),('06344ba171c54534b718a4955f804c5f','a564f8a75e77469e97845139078a46cb','待办任务','work/task/todolistpage','',1,'',1,'2018-12-10 15:55:51','2018-12-17 16:26:42','1','1','1'),('1','0','个人考勤','','',0,'menu-icon fa fa-pencil-square-o',1,'2018-07-01 14:47:30','2018-08-19 15:41:49','1','1','1'),('10','9','模型管理','actmodel/modellistpage','',1,'',1,'2018-08-11 02:17:16','2018-12-06 09:40:52','1','1','1'),('2','1','我的考勤','attendSys/myattpage',NULL,1,NULL,1,'2018-07-02 14:49:29',NULL,'1',NULL,'1'),('3','1','考勤设置','attendSys/sysattpage',NULL,1,NULL,2,'2018-07-03 14:50:33',NULL,'1',NULL,'1'),('4','1','考勤查询','#',NULL,1,NULL,3,'2018-07-04 14:51:27',NULL,'1',NULL,'1'),('5','0','系统设置','','',0,'menu-icon fa fa-desktop',2,'2018-07-04 14:52:58','2018-08-19 15:41:56','1','1','1'),('59e9ff8e5bf84a0a9485df3ef71d0ce4','a564f8a75e77469e97845139078a46cb','发起任务','work/task/startworklistpage','',1,'',0,'2018-12-10 15:55:22','2018-12-13 15:15:53','1','1','1'),('6','5','用户管理','user/sysuser','',1,'',1,'2018-07-05 14:53:46','2018-08-12 22:00:38','1','1','1'),('6d01e24346374f95be28f6b40a048728','5','角色管理','role/sysrole','',1,'',4,'2018-08-12 13:21:22',NULL,'1',NULL,'1'),('7','5','菜单管理','menu/sysmenu',NULL,1,NULL,2,'2018-07-06 14:54:36',NULL,'1',NULL,'1'),('8','5','部门管理','dept/sysdept',NULL,1,NULL,3,'2018-07-07 14:55:21',NULL,'1',NULL,'1'),('838576fe2c33446f968a5cc919dd1274','9','流程管理','actprocess/processlistpage','',1,'',2,'2018-08-13 14:41:49','2018-12-06 18:45:13','1','1','1'),('9','0','工作流程','','',0,'menu-icon fa fa-list-alt',3,'2018-08-11 02:16:35','2018-08-19 15:42:03','1','1','1'),('a564f8a75e77469e97845139078a46cb','0','在线办公','','',0,'menu-icon glyphicon glyphicon-list',0,'2018-12-10 15:54:54','2018-12-10 16:01:30','1','1','1'),('da525874a33a44698ad4b20d7eb2372f','5','字典管理','dict/showindex','',1,'',5,'2018-08-20 12:48:14',NULL,'1',NULL,'1');

/*Table structure for table `tab_sys_role` */

DROP TABLE IF EXISTS `tab_sys_role`;

CREATE TABLE `tab_sys_role` (
  `id` varchar(50) NOT NULL COMMENT '主键id',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `mark` varchar(2000) DEFAULT NULL COMMENT '角色备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_id` varchar(50) DEFAULT NULL COMMENT '创建人id',
  `update_id` varchar(50) DEFAULT NULL COMMENT '修改人id',
  `delete_flag` varchar(1) DEFAULT NULL COMMENT '删除标记(0.删除--1.正常)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_sys_role` */

insert  into `tab_sys_role`(`id`,`role_name`,`mark`,`create_time`,`update_time`,`create_id`,`update_id`,`delete_flag`) values ('1','系统管理员','系统管理员','2018-08-01 14:56:28',NULL,'1',NULL,'1'),('2f6b00f0d79444e4bb92d114a054d5aa','工作流程专用','工作流程专用','2018-12-17 17:39:26',NULL,'1',NULL,'1'),('4e9d699a85644ac98e2533a910e4594a','测试角色','测试角色','2018-08-19 15:17:53',NULL,'1',NULL,'1'),('7eaedc35f925465da77895cebf6ae545','普通员工','普通员工','2018-08-12 21:58:05',NULL,'1',NULL,'1');

/*Table structure for table `tab_sys_role_menu` */

DROP TABLE IF EXISTS `tab_sys_role_menu`;

CREATE TABLE `tab_sys_role_menu` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `role_id` varchar(50) DEFAULT NULL COMMENT '角色id',
  `menu_id` varchar(50) DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_sys_role_menu` */

insert  into `tab_sys_role_menu`(`id`,`role_id`,`menu_id`) values ('02745af8ba504ea5a3865ef2b2b4828b','7eaedc35f925465da77895cebf6ae545','2'),('1046d5bc03644c9491fdbd5adcb5ec6a','1','a564f8a75e77469e97845139078a46cb'),('13f5b7851b9f4588a9a409857d7aaa1b','1','9'),('142b0bf9f5614a37ae803b9d4f660853','4e9d699a85644ac98e2533a910e4594a','59e9ff8e5bf84a0a9485df3ef71d0ce4'),('19b6149a13d944d0b58dcb6cee354622','1','4'),('2ad8ebfb97eb43d09ae5f4c723c79bcd','1','10'),('319d7a41be6a43b1807c477c0ee36f5a','7eaedc35f925465da77895cebf6ae545','a564f8a75e77469e97845139078a46cb'),('32417e5f275249ecae315530a050262f','1','6d01e24346374f95be28f6b40a048728'),('356ddb209bce4710b56f13a2bf97cbc5','4e9d699a85644ac98e2533a910e4594a','a564f8a75e77469e97845139078a46cb'),('35db209c6cf3444fafc3cedc60fc080c','1','838576fe2c33446f968a5cc919dd1274'),('3628af13f33b42dd92cecf1ca8839584','7eaedc35f925465da77895cebf6ae545','1'),('365b138aa88f4da5bfc9cc972cc1e37e','2f6b00f0d79444e4bb92d114a054d5aa','03f3dcc0af964f5ca191114a0d946250'),('4a524c0a4bef4b49a752778c397e45ea','4e9d699a85644ac98e2533a910e4594a','4'),('505cf01bca4546c88c8fbfc0e4a416db','7eaedc35f925465da77895cebf6ae545','03f3dcc0af964f5ca191114a0d946250'),('56153896397e4de98de48df30d90bb4c','1','da525874a33a44698ad4b20d7eb2372f'),('5657ef6e85444d4197d2ea66a354f93e','4e9d699a85644ac98e2533a910e4594a','06344ba171c54534b718a4955f804c5f'),('5f3f5d3a5ae1423daa9a80b01fad0ec3','4e9d699a85644ac98e2533a910e4594a','1'),('62af567342bd4cb7b35cf8d8b435fd88','2f6b00f0d79444e4bb92d114a054d5aa','a564f8a75e77469e97845139078a46cb'),('714b63cf55aa40938b473de39e61e2d1','1','1'),('8019f28bc17f4b3496966183fca211fa','1','06344ba171c54534b718a4955f804c5f'),('9604ca27cb11423fae9d550f48cca677','7eaedc35f925465da77895cebf6ae545','06344ba171c54534b718a4955f804c5f'),('992434997d994ac69b00a2d572efef4f','1','8'),('a2ff64bc1b784d8cbfd56814d22e70b8','1','6'),('ad1837fd6d7a4d1991bdbadee72fd1e9','1','3'),('bd1627a53ae74593b85154bb83d398e2','1','03f3dcc0af964f5ca191114a0d946250'),('c15ef9361c544a90a75ced24f4c61026','1','2'),('c868cf281d3b4b02bda69e3e812c5898','4e9d699a85644ac98e2533a910e4594a','2'),('cb1a5b525fb140eeaaa02671b0185f2b','1','7'),('d89fe3cc3da04bdd9cbeee4a40373d6b','1','59e9ff8e5bf84a0a9485df3ef71d0ce4'),('d90013c98cd54235a9c74c90aa822f31','2f6b00f0d79444e4bb92d114a054d5aa','838576fe2c33446f968a5cc919dd1274'),('dddab01cdeb5428f9793d76b802de493','4e9d699a85644ac98e2533a910e4594a','3'),('df58314b9a244029b559cd90052b741d','2f6b00f0d79444e4bb92d114a054d5aa','59e9ff8e5bf84a0a9485df3ef71d0ce4'),('e0e9a59cfe43429aa9108091ad2f208d','7eaedc35f925465da77895cebf6ae545','59e9ff8e5bf84a0a9485df3ef71d0ce4'),('e58561bfac894dcd8c5a6d201b9f2eb1','1','5'),('ea8a21189cda4b14aad2e2a188e4b68c','2f6b00f0d79444e4bb92d114a054d5aa','9'),('ebcaa327332a4d4684f5d75d88f362af','2f6b00f0d79444e4bb92d114a054d5aa','10'),('fafe84683ac84699b8d0a3d6f06c7da6','4e9d699a85644ac98e2533a910e4594a','03f3dcc0af964f5ca191114a0d946250'),('fe457201edb749f1915453ae018822df','2f6b00f0d79444e4bb92d114a054d5aa','06344ba171c54534b718a4955f804c5f');

/*Table structure for table `tab_sys_user` */

DROP TABLE IF EXISTS `tab_sys_user`;

CREATE TABLE `tab_sys_user` (
  `id` varchar(255) NOT NULL COMMENT '用户id',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `tel` varchar(255) DEFAULT NULL COMMENT '电话',
  `dept_id` varchar(255) DEFAULT NULL COMMENT '部门id',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `salt` varchar(255) DEFAULT NULL COMMENT '加密盐',
  `status` varchar(10) DEFAULT '1' COMMENT '状态标记0.禁用--1.正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_id` varchar(255) DEFAULT NULL COMMENT '创建人id',
  `update_id` varchar(255) DEFAULT NULL COMMENT '修改人id',
  `delete_flag` varchar(255) DEFAULT '1' COMMENT '删除标记(1正常,2,删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_sys_user` */

insert  into `tab_sys_user`(`id`,`username`,`password`,`sex`,`tel`,`dept_id`,`email`,`address`,`salt`,`status`,`create_time`,`update_time`,`create_id`,`update_id`,`delete_flag`) values ('075ebcea6446476998c82b96e0662e7f','童彦清','c468a2b2d249726cf1e24d4d252060619d50635cbcdadaaed1e778a1cdb61474','1','','8','',NULL,'iAqFMCEyRMPlAf6j7bCx','1','2018-08-20 12:46:50','2018-12-20 16:21:59','1','1','1'),('0ee035ff27f540f78a3c8ec515a22db1','王成文','0fac09a8e3c89068311ae6f918b44e9f22347674cd55a5ea2588f68ba7df57cd','0','13269541236','8','',NULL,'4kmJaxvhGEb7WLqx4GQ7','1','2018-12-14 16:10:51','2018-12-21 18:27:48','1','1','1'),('1','admin','e0e0a3c32f79d6986f8fbf9f9dfe122bb1d147feebe21fbaa2e7732eec6c26f7','0','18789458141','1','makunlovelove@163.com','陕西省西安市未央区','cl7GQ1AkHJSLEQ5rLVZm','1','2018-08-20 12:46:53','2018-12-14 16:08:05','1','1','1'),('2','马堃','e0e0a3c32f79d6986f8fbf9f9dfe122bb1d147feebe21fbaa2e7732eec6c26f7','1','18789458141','1','makunlovelove@163.com','陕西省西安市未央区','cl7GQ1AkHJSLEQ5rLVZm','1','2018-08-20 12:46:50','2018-12-21 18:27:39','1','1','1'),('500a1b9e22084d5da55a16bd67300369','曹园诗','96d4aa0469711b008a288e080d86467a73fa767c94e5532ce9ee2ac6b594054a','0','','8','',NULL,'P0HSfFJVATOuo5JmzHom','1','2018-12-14 16:10:26','2018-12-14 17:59:42','1','1','1'),('c9e3202c3f9a4b5da6d4c8aede8ac807','周启','b6f680fa63aaca25f596829fadaa594a791c3948dcc32e67657bb67936f98cdf','0','','7','',NULL,'lohhkIHZDFQNHzWAWHax','1','2018-12-14 16:09:59',NULL,'1',NULL,'1');

/*Table structure for table `tab_sys_user_role` */

DROP TABLE IF EXISTS `tab_sys_user_role`;

CREATE TABLE `tab_sys_user_role` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(50) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_sys_user_role` */

insert  into `tab_sys_user_role`(`id`,`user_id`,`role_id`) values ('0062edeaee8245e096d50d002a1bd642','0ee035ff27f540f78a3c8ec515a22db1','7eaedc35f925465da77895cebf6ae545'),('20591281d9514393a287d9754b052919','0ee035ff27f540f78a3c8ec515a22db1','4e9d699a85644ac98e2533a910e4594a'),('3bbaa4d991e0434aab27b28b48202ffa','500a1b9e22084d5da55a16bd67300369','7eaedc35f925465da77895cebf6ae545'),('6f24d76d5a4b4fba8c312ecc4d1e3c45','2','4e9d699a85644ac98e2533a910e4594a'),('72f99e61e0d5471b831e5a0dfeab6ef9','075ebcea6446476998c82b96e0662e7f','7eaedc35f925465da77895cebf6ae545'),('8c43aa49878f46a1b48655a7729dfa55','075ebcea6446476998c82b96e0662e7f','2f6b00f0d79444e4bb92d114a054d5aa'),('cb5e8a51d90b467bad4f166f3dfd6501','2','2f6b00f0d79444e4bb92d114a054d5aa'),('fcd42f9c71604c98b5f29469f0a70936','c9e3202c3f9a4b5da6d4c8aede8ac807','4e9d699a85644ac98e2533a910e4594a');

/*Table structure for table `tab_workflow_creatorconfine` */

DROP TABLE IF EXISTS `tab_workflow_creatorconfine`;

CREATE TABLE `tab_workflow_creatorconfine` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `model_id` varchar(50) DEFAULT NULL COMMENT '模型id',
  `process_key` varchar(50) DEFAULT NULL COMMENT '流程key',
  `role_ids` varchar(2000) DEFAULT NULL COMMENT '角色ids支持多个角色用,分隔',
  `user_ids` varchar(2000) DEFAULT NULL COMMENT '用户ids支持多个用户用,分隔',
  `create_id` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` varchar(10) DEFAULT '1' COMMENT '删除标记--0表示删除；1表示正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_workflow_creatorconfine` */

insert  into `tab_workflow_creatorconfine`(`id`,`model_id`,`process_key`,`role_ids`,`user_ids`,`create_id`,`create_time`,`update_id`,`update_time`,`del_flag`) values ('6b412f2b84bc491cbb0c335fce20bb49','112501','leave','','1,','1','2018-12-21 16:31:39','1','2018-12-21 18:15:22','1');

/*Table structure for table `tab_workflow_designform` */

DROP TABLE IF EXISTS `tab_workflow_designform`;

CREATE TABLE `tab_workflow_designform` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_workflow_designform` */

/*Table structure for table `tab_workflow_task` */

DROP TABLE IF EXISTS `tab_workflow_task`;

CREATE TABLE `tab_workflow_task` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_workflow_task` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
