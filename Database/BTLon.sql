-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 21, 2015 at 09:41 PM
-- Server version: 5.5.44-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `BTLon`
--

-- --------------------------------------------------------

--
-- Table structure for table `Book`
--

CREATE TABLE IF NOT EXISTS `Book` (
  `BID` int(11) NOT NULL COMMENT 'book id',
  `Name_Book` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Author` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Price` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Content` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`BID`),
  UNIQUE KEY `BID_2` (`BID`),
  KEY `BID` (`BID`),
  KEY `BID_3` (`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Book`
--

INSERT INTO `Book` (`BID`, `Name_Book`, `Author`, `Price`, `Quantity`, `Content`) VALUES
(1, 'Có những bàn tay', 'September Rain', 59000, 10, 'Đã quá trễ,chuyến tàu em rời bến\r\n\r\nanh lên không? còn chỗ trống cuối cùng\r\n\r\nnhanh lên nhé,sợ không còn kịp nữa\r\n\r\nchuyến tàu em sắp sửa chuyển sang đông!\r\n\r\nĐã quá trễ,anh có muốn lên không\r\n\r\ncánh cửa ấy sắp đóng sầm\r\n\r\nthinh lặng\r\n\r\nlối rẽ vào đêm là con đường xa vắng\r\n\r\nlối rẽ lòng em là một nỗi xa xăm”\r\n\r\n\r\nTa có quyền yêu bằng cả con tim nồng nhiệt căng tràn, không ngại ngần, không sợ đón nhận tổn thương. Là bởi thanh xuân, là bởi vẻ đẹp chỉ một lần tỏa sáng trong đời.\r\n\r\n"Có những bàn tay không chạm được, dẫu gần\r\n\r\nMùa thu cũ dẫu gần mùa đông mới\r\n\r\nEm đã nhoài tìm bàn tay anh với\r\n\r\nChỉ để buồn thôi,\r\n\r\nChỉ biết đã xa xôi!"\r\n'),
(2, 'Mật Mã Thanh Minh Thượng Hà ', 'Dã Văn Bưu', 135000, 15, 'Bộ tiểu thuyết Mật mã Thanh minh thượng hà được hư cấu từ bức tranh cổ “Thanh minh thượng hà đồ”, bản gốc thời Tống của Trương Trạch Đoan. Các câu chuyện trong đó được dựng lên từ chính các nhân vật được vẽ trong bức tranh cổ. Bức tranh có 824 nhân vật vô danh, giờ đây mỗi nhân vật đều có tên có họ. Họ cải trang, mai phục trong ghe thuyền xe kiệu cho tới quán rượu quán trà. Quang cảnh tưởng chừng như thái bình thịnh trị, kì thực nguy hiểm đang rình rập bốn bề. Trong tiếng rao của người bán hàng rong, những tên gián điệp, thích khách của các nước Kim, Liêu, Tây Hạ, Cao Ly đã dần dần nhập cuộc, 824 nhân vật sống dậy từng người từng người một, chỉ còn đợi chiếc thuyền chở khách trôi qua gầm cầu cong cong kia, thước phim về sự sụp đổ của vương triều Bắc Tống sẽ được mở màn. Đây chính là mỹ cảnh tuyệt đẹp nhất về sự thịnh trị của nhà Bắc Tống, hơi thở của sự diệt vong đang ẩn khuất trong khói sóng trên dòng Biện Hà: “Chính giữa bức tranh, giữa đám tàu thuyền tấp nập trên Biện Hà, một con thuyền trông bình thường như bao con thuyền khác đang từ từ trôi qua gầm cầu, thế nhưng vì không kịp hạ cọc buồm xuống, chiếc thuyền như sắp đâm vào cầu tới nơi. Trên thuyền mọi người tay chân cuống loại, bên bờ kêu hô ầm ĩ, trong cơn hỗn loạn, một bóng lạ lướt qua, một trận khói sương mù mịt ập tới, đến khi khói tan sương nhạt, trên thuyền chỉ còn lại 24 thi thể, tất cả mọi người đều chỉ biết há hốc miệng không tin vào mắt mình...”'),
(3, 'Steve Jobs', 'Brent Schlender, Rick Tetzeli', 155000, 13, 'Vào một buổi chiều tháng mười hai lạnh lẽo năm 1979, Steve Jobs dừng xe tại bãi đậu xe trong Khu vườn của Thánh A-La (Garden of Allah), một trung tâm phục vụ hội nghị và tĩnh dưỡng trên sườn ngọn núi Mount Tamalpais của hạt Marin, phía Bắc San Francisco. Trông anh mệt mỏi, thất vọng, tức giận và đến muộn. Giao thông trên đường 280 và 101 bị tắc nghẽn nhiều nơi trên đường lên từ Cupertino, và đường xuống phía nam thung lũng Sillicon, nơi đặt trụ sở chính của công ty anh sáng lập, Apple Computer, và cũng là nơi anh vừa phải trải qua một cuộc họp hội đồng quản trị Apple dưới sự chủ trì của Arthur Rock đáng kính. Anh và Rock có rất nhiều bất đồng ý kiến. Rock đối xử với anh như một đứa con nít. Rock ưa cấp bậc còn anh chuộng các quá trình, anh tin rằng các công ty công nghệ phát triển theo những cách và nguyên tắc nhất định. Anh có niềm tin này do đã quan sát thấy nó phát huy tác dụng, đáng chú ý nhất là tại Intel, hãng sản xuất chip vĩ đại có trụ sở tại Santa Clara mà anh ủng hộ ngay từ những ngày đầu. Rock có lẽ là nhà đầu tư công nghệ đáng chú ý nhất trong thời đại của mình, nhưng thực tế thì ban đầu ông đã trợ giúp Apple một cách miễn cưỡng, phần nhiều bởi vì ông không vừa mắt với Steve và đồng sự Steve Wozniak. Ông không nhìn Apple theo cách mà Jobs vẫn nhìn – Steve xem Apple là một công ty khác thường đang thực hiện công việc nhân tính hóa điện toán với một cơ cấu tổ chức không có tôn ti trật tự. Rock thì đơn giản coi Apple như một vụ đầu tư không hơn không kém. Steve cảm thấy các cuộc họp ban điều hành với Rock không tạo ra động lực và chỉ khiến anh thêm nhụt chí; anh đã trông đợi chuyến đi dài trên chiếc xe mui trần phóng tới Marin sẽ giúp anh thoát khỏi mùi hôi thối cũ rích của cuộc tranh luận kéo dài dường như tới vô tận.'),
(4, 'Triệu Phú Khởi Nghiệp Từ Hai Bàn Tay Trắng', 'Triệu Phàm Vũ', 130000, 12, 'Doanh nhân nổi tiếng Trương Cận Đông nói: "Nhân phẩm là chỗ đứng của một người trong xã hội, đặt vào trong kinh doanh, điều đó là sự thể hiện trách nhiệm đối với xã hội của một doanh nghiệp". Có trách nhiệm với xã hội, đem tiền bạc đáp trả xã hội, để ngày càng có nhiều người được hướng lợi, là cách làm độc đáo của không ít các doanh nghiệp thành công. Triêu Phú Khởi Nghiệp Từ Hai Bàn Tay Trắng là cuốn sách thông qua những ví dụ điển hình để truyền tải đến mọi người tinh thần, phương pháp và cách thức làm giàu; với mục đích bồi đắp ý chí làm giàu; cái nhìn nhanh nhạy, tư duy kinh doanh linh hoạt, tài trí hơn người và khả năng vận dụng phương thức kinh doanh một cách khéo léo.\r\n\r\nTriệu phú khởi nghiệp từ hai bàn tay trắng là tuyển tập các câu chuyện về những người thành công trên toàn thế giới, nhưng ở họ có chung một xuất phát điểm: không có gì để nương tựa. Vậy bằng cách nào, con người ta có thể thay đổi cuộc đời của chính mình chỉ nhờ hai bàn tay ấy? Hãy cùng tìm kiếm câu trả lời qua cuốn sách ý nghĩa này.\r\n'),
(5, 'Tôi Là Bêtô', 'Nguyễn Nhật Ánh', 40000, 14, 'Tên tôi là Bêtô. Đó là cái tên chị Ni đặt cho tôi. Đúng ra, cái tên ban đầu là Bêbêtô. Bạn có biết Bêbêtô là gì không? Chắc bạn nghĩ đó là một loại củ cải? Trật lất rồi, bạn ơi. Đó là tên của một cầu thủ đội Brazil. \r\nChị Ni đặt cho tôi cái tên đó chính xác là vào ngày 15 tháng 7 năm 1994, tức là đúng nửa tháng sau ngày đội tuyển Brazil đoạt chức vô địch giải bóng đá thế giới lần thứ 15. \r\nĐó cũng chính là ngày tôi đặt chân đến nhà tôi. Tất nhiên là nhà tôi hiện nay. Nhà tôi hiện nay tất nhiên là nhà tôi đang ở. \r\nPhải nói rõ như vậy, vì thông thường, khi nhắc đến hai chữ "nhà tôi" người ta đang nghĩ đến cái nhà người ta đã rời xa. \r\nNgười ta nghĩ đến cái nhà cách đó một trăm mét và lo lắng nói: \r\n- Mưa thế này, không biết nhà tôi có dột không. \r\nNgười ta nghĩ đến cái nhà cách đó một ngàn mét và buồn bã nói: \r\n- Không biết giờ này ở nhà tôi mọi người đang làm gì. \r\nNgười ta nghĩ đến cái nhà cách đó nửa vòng trái và lần này thì người ta sụt sùi: \r\n- Chẳng biết chừng nào tôi mới được về thăm lại nhà tôi. \r\nNgày đầu tiên về thăm quê từ một nơi xa lăng lắc, chính bà nội của chị Ni vừa khóc vừa kể như vậy. Lúc đó tôi đang nằm gặm xương dưới gầm bàn chứ đâu. \r\n \r\n\r\n2 \r\nBêbêtô! Cái tên cũng hay đấy chứ? Nhưng dài quá. \r\nĐầu tiên chị Ni gọi tôi: \r\n- Bêbêtô. \r\nSau đó, anh Nghé, anh chị Ni, gọi tôi: \r\n- B... bêtô. \r\nĐến lượt ba chị Ni và mẹ chị Ni gọi tôi: \r\n- Bêtô. \r\nBạn thấy chưa, càng lớn tuổi, con người ta càng nói ít đi. Họ nghĩ nhiều hơn. \r\nThế là tôi trở thành Bêtô. \r\nMay mà bà nội chị Ni về chơi ít ngày, chưa kịp gọi đến tôi. Nếu không tên tôi có khi chỉ gọn lỏn: \r\n- Tô. \r\nMà như thế thì thật buồn cười. \r\n \r\n'),
(6, 'Candy Book - Chuyến Đi Thực Tế Đầu Tiên Của Tôi\r\n', 'Dream Cartoon', 52000, 7, 'Candy Book hiện là bộ truyện tranh Hàn Quốc được ưa thích nhất dành cho lứa tuổi học trò tại khắp các quốc gia châu Á như: Hàn Quốc, Nhật Bản, Indonesia, Malaysia…. Được sáng tác bởi đội ngũ họa sỹ, nhà văn trẻ nổi tiếng của Hàn Quốc, bộ truyện đã gây được tiếng vang ngay khi mới phát hành và nhanh chóng trở thành bộ truyện yêu thích và săn đón của các em nhỏ.\r\n\r\nLần đầu tiên có mặt tại Việt Nam, Bộ truyện tranh Candy Book với hình ảnh đẹp, nội dung hấp dẫn mang đến cả thế giới sắc màu ngọt ngào hứa hẹn sẽ gây sốt cho lứa tuổi học trò Việt. Bộ truyện gồm nhiều tập trong đó mỗi tập là một câu chuyện với hệ thống nhân vật và nội dung độc lập. Các nhân vật trong bộ truyện là những cô cậu học trò tinh nghịch, hồn nhiên, nhiều mộng mơ và sáng tạo. Từ những câu chuyện ở trường, những tình cảm học trò hồn nhiên trong sáng hay những xích mích nhỏ giữa các nhân vật, các em nhỏ sẽ hiểu hơn về tình bạn, tình cảm chớm nở của lứa tuổi học trò, những giá trị cuộc sống tốt đẹp.\r\n\r\nĐặc biệt, các bộ truyện được phát hành tại Việt Nam sẽ được cập nhật song song cùng thời điểm các bộ truyện vừa ra mắt “nóng hổi” tại Hàn Quốc. Teen Việt Nam sẽ được trải nghiệm cảm giác hồi hộp, thú vị khi cùng các bạn teen nhiều nước trên Thế giới đón chờ những tập truyện mới nhất, những chủ đề, những nhân vật sắp được ra mắt. Lần ra mắt đầu tiên tại Việt Nam, 5 tập Candybook đầu tiên với các tựa đề hấp dẫn như: Hội diễn văn nghệ, Mãi giữ những trang lưu bút, Thiên thần hộ mệnh hay Thời đại của các cô gái sẽ mang đến cho teen Việt cả thế giới sắc màu ngọt ngào.\r\n\r\n"Tớ đã có một chuyến đi thực tế thật khó quên, bời chuyến đi không chỉ giúp tờ hiếu thêm về thiên nhiên và văn hóa ở nơi đó, mà còn mang cho tớ và bạn bè rất nhiều kỉ niệm đẹp. Chúng tớ nỗ lực hết mình để giành giải nhất văn nghệ, chúng tơ đã trải qua những hiểu lầm, giận hờn và cả những tình cảm học trò trong sáng nữa..."'),
(7, 'Ca, P, Mg Có Gì Hay?\r\n', 'BS. Lương Lễ Hoàng', 78000, 5, 'Với độc giả trong và ngoài nước "thương hiệu Lương Lễ Hoàng" đã từ lâu đồng nghĩa với văn phong “y khoa cho mọi người” trên truyền thông đại chúng.\r\n\r\nVới quan điểm “nói có sách” không bằng “mách có chứng”, tác giả của hơn 300 bài báo và hàng trăm chương trình truyền hình, truyền thanh... thêm một lần đúc kết kinh nghiệm với “khoáng tố liệu pháp”, với phác đồ kết hợp canxi, phốt-pho và ma-nhê trong quá trình điều trị hàng ngàn bệnh nhân mãn tính.“\r\n\r\nCa+P+Mg có gì hay? không chỉ xoay quanh trọng điểm là mặt mạnh của 3 khoáng tố đại lượng gắn liền với chất lượng cuộc sống. Với ấn phẩm này, người đọc có trong tay thông tin ngắn gọn nhưng cụ thể về những yếu tố bào mòn sức đề kháng.\r\n\r\nĐộc giả nhờ đó không quá sợ bệnh vì hiểu bệnh cũng như biết cách mượn sức kháng bệnh của hoạt chất sinh học.Đó là lý do tại sao Ca+P+Mg có gì hay? không thể thiếu trong tủ sách “Y khoa ai đọc cũng hiểu”.'),
(8, 'Câu Chuyện Nhỏ - Trí Tuệ Lớn\r\n', 'Bùi Thị Thiên Thai', 36000, 11, 'Trong cuộc sống muôn màu của chúng ta hiện nay sản sinh ra rất nhiều những câu chuyện ngụ ngôn mang tính triết lí phong phú chẳng thua kém gì những câu chuyện ngụ ngôn kinh điển. Nó xứng đáng để chúng ta phải tĩnh lại, bỏ qua những ồn ào vội vã của cuộc sống để đọc, thưởng thức và suy ngẫm.\r\n\r\n\r\nMột câu chuyện hay có thể ảnh hưởng đến cuộc đời của mỗi con người. Cuốn sách chỉ ra cho bạn cách hành xử và cách làm người. Qua những câu chuyện nhỏ, chúng ta có thể tìm thấy bóng dáng của cuộc sống hiện thực. Có những câu chuyện mà đọc xong khiến bạn như bừng tỉnh khỏi cơn mộng mị, có những câu chuyện khiến bạn phải bật cười, có những câu chuyện bạn cảm thấy vô cùng quen thuộc mà sao vẫn luôn mới mẻ...\r\n\r\n\r\nCuốn sách dành cho độc giả là thanh thiếu niên và cả những người trưởng thành, giúp bạn thu hoạch được nguồn trí tuệ dồi dào của nhân loại.\r\n'),
(9, 'Sống Tích Cực Để Yêu Thương\r\n', 'Nguyễn Tuấn Quỳnh', 83300, 20, '"Dù bạn có đầy đủ vật chất hay đỉnh cao danh vọng, cuộc sống này sẽ vẫn là vô nghĩa nếu không có tình yêu thương, niềm hy vọng sự sẻ chia và những ước mơ cháy bỏng.Giống như con diều muốn bay cáo trên bầu trời, yếu tố của nó phải là thiết kế gọn nhẹ. Con người muốn bước dài trên chuyến hành trình cuộc đời cần gạt bỏ những thứ làm tâm hồn mình ưu tư, phiền muộn và những thứ khiến đôi chân nặng nề, mệt mỏi."\r\n\r\n(Nguyễn Tuấn Quỳnh)\r\n\r\nCuốn sách với những câu chuyện nhỏ nhưng hết sức ý nghĩa về giá trị cuộc sống. Hạnh phúc thật sự trong cuộc đời mỗi người là gì? Những nguyên tắc để sống hạnh phúc? Có phải cho đi là luôn được nhận lại? Và nên chăng, thứ tha là cách để bạn tìm thấy sự an yên trong tâm hồn?\r\n\r\nBạn sẽ bắt gặp trong cuốn sách này những chia sẻ mang tính chất trải nghiệm, những triết lý nhẹ nhàng mà sâu sắc, thấm thía: "Chúng ta chỉ có thể trưởng thành hơn nếu chấp nhận thay đổi... Hãy ném mình ra thế giới rộng lớn. Hãy làm điều đó. Hãy ném mình đi."'),
(10, 'Dược Học Tham Luận\r\n', 'Chơn Nguyên', 90000, 18, '"Hỏi: Thuốc là côn trùng đất đá,rễ,cỏ,da cây,v..v cùng người khác loại mà có thể trị được bệnh cho con người là tại sao?\r\n\r\nĐáp: Trời đất chỉ là hai khí âm dương lưu hành mà thành năm vận, đối chọi với nhau mà thành sáu khí. Con người sinh ra gốc ở trời mà gần với đất, tức là bẫm năm vận sáu khí của trời đất để sinh năm tạng sáu phủ. Vật tuy cùng người khác loại, nhưng đều gốc từ một khí của trời đất mà sinh. Mỗi vật riêng được một khí thiên lệch, còn người thì được trọn vẹn khí của trời đất vậy.Một khi khí trong thân người chênh lệch thì sinh ra bệnh tật, thì lại mượn món thuốc thiên lệch một khí để điều chỉnh sự nghiêng lệch trong thân ta cho trở về với quân bình thì hết bệnh vậy. Vả chăng mượn âm dương của vật để biến hóa âm dương trong thân người, nên căn cứ vào đó Thần Nông dùng thuốc để chữa bệnh."'),
(11, 'Góc Nhìn Sử Việt - Đất Nước Việt Nam Qua Các Đời\r\n', 'Đào Duy Anh', 97000, 13, 'Lịch sử văn hóa của một dân tộc không phải của riêng cá nhân nào, chính vì vậy, việc bảo tồn, gìn giữ và phát triển lịch sử văn hóa cũng không phải riêng một người nào có thể gánh vác được, nó thuộc về nhận thức chung của toàn xã hội và vai trò của từng nhân tố trong mỗi chặng đường lịch sử. Lịch sử là một khoa học. Lịch sử không phải là việc thống kê sự kiện một cách khô khan rời rạc. Bởi mỗi sự kiện trong tiến trình đó đều có mối liên kết chặt chẽ với nhau bằng sợi dây vô hình xuyên suốt không gian và thời gian tạo nên lịch sử của một dân tộc.\r\n\r\nTheo nhiều kết quả khảo sát, đánh giá nhu cầu của bạn đọc cho thấy, “lỗ hổng lịch sử” ở không ít người trẻ hiện nay hoàn toàn có thể bù lấp một phần dựa trên nhiều nguồn tư liệu, công trình nghiên cứu, sách cổ sách quý hiện đang được các Viện nghiên cứu, các tổ chức, cá nhân lưu giữ. Tủ sách Góc nhìn sử Việt với mục đích chung tay tái hiện một cách rõ nét những mảnh ghép lịch sử dân tộc.\r\n\r\nPhạm vi nghiên cứu của địa lý học lịch sử rất rộng. Đất nước Việt Nam qua các đời chỉ nhằm phục vụ những yêu cầu trực tiếp của việc nghiên cứu và giảng dạy lịch sử Việt Nam đề ra những vấn đề có thể nói là sơ bộ, từ đó mà hiểu thêm được về phương diện địa lý những sự kiện quan trọng của thông sử Việt Nam. Chủ yếu là nghiên cứu phần địa lý hành chính để nhận định cương vực của nhà nước và vị trí các khu vực hành chính trải qua các đời, rồi đến quá trình mở mang lãnh thổ. Đồng thời tác giả lại nghiên cứu khía cạnh địa lý của những cuộc chiến tranh chống ngoại xâm quan trọng thời phong kiến, tức là sự nghiệp bảo toàn đất nước trải qua các đời.'),
(12, 'Từ Điển Hàn - Việt (Khoảng 120.000 Mục Từ) - Bìa Đỏ\r\n', 'Lê Huy Khoa', 294000, 5, 'Để đáp ứng kịp thời nhu cầu tra cứu và học tập tiếng Hàn ở Việt Nam hiện nay, tác giả Lê Huy Khoa đã dày công nghiên cứu và dồn nhiều công sức trong nhiều năm qua để biên soạn cuốn Từ điển Hàn - Việt với quy mô lớn hơn, hiện đại hơn, lượng từ phong phú và đầy đủ hơn.\r\n\r\n- Khoảng 120000 mục từ, ghi từ gốc Hán, từ ngoại lai\r\n\r\n- Giải nghĩa đầy đủ mọi ngữ nghĩa, câu ví dụ phong phú\r\n\r\n- Mục lục bao gồm phần ngữ pháp, phát âm, lượng từ, số từ, giới thiệu cơ bản về Hàn Quốc, từ đồng nghĩa trái nghĩa, động-tính từ bất quy tắc,…\r\n\r\nLà quyển từ điển lớn nhất, chi tiết, đầy đủ và giới thiệu tổng thể về tiếng Hàn QuốcCuốn Từ điển Hàn - Việt của tác giả Lê Huy Khoa là một công trình nghiên cứu khoa học công phú, một thành quả lao động miệt mài, say mê và sáng tạo.\r\n\r\nCuốn từ điển được thiết kế đẹp mắt, chắc chắn với bìa cứng đỏ, cùng với đó là chất liệu giấy in tốt nhất sẽ đam đến cho độc giả những trải nghiệm tuyệt vời nhất .'),
(13, 'Deutsch Wodoku: Vui Học Từ Vựng Với Ô Chữ Sudoku\r\n', 'Elke Huppertz', 198000, 7, ''),
(14, 'Con Hoang\r\n', 'Lê Hồng Nguyên', 55000, 8, 'Quyển sách Deutsch Wodoku: Vui Học Từ Vựng Với Ô Chữ Sudoku sẽ mang đến cho bạn niềm hứng thú khi học từ vựng theo cách khác biệt. Bạn có thể học khoảng 1000 từ vựng tiếng Đức thuộc nhiều chủ đề (nghề nghiệp, quần áo, thời tiết, máy vi tính,…), ôn lại từ và củng cố khả năng nhớ từ bằng cách giải ô chữ từ vựng Sudoku\r\n\r\nBạn có biết trò chơi Sudoku – Trò chơi dạng câu đố logic có xuất xứ từ Nhật Bản?\r\n\r\nTrò chơi Sudoku gồm 2 loại: Sudoku 9 và Sudoku 6. Sudoku 9 là một hình vuông lớn gồm 9 hàng và 9 cột, được chia thành 9 vùng. Mỗi hàng, cột và vùng phải chứa tất cả các chữ số từ 1 đến 9; mỗi chữ số chỉ xuất hiện một lần trong mỗi hàng, cột và vùng. Sudoku 6 có hình thức đơn giản hơn, chỉ bao gồm 6 hàng, 6 cột và 6 vùng.\r\n\r\nNếu thay số bằng chữ trong các ô Sudoku thì chúng ta sẽ có Wodoku. Wodoku cũng bao gồm 2 loại là Wodoku 9 và Wodoku 6. Trong Wodoku, mỗi từ cũng chỉ xuất hiện một lần ở mỗi hàng, cột và vùng. Để bạn dễ theo dõi và dễ học khi giải câu đó, cuốn sách sử dụng nhiều màu cho các ô chứa các từ khác nhau.\r\n\r\nWodoku sẽ có thực hành bằng cách yêu cầu bạn điền từ thích hợp vào các ô trống, sau đó đọc to những từ đó. Việc này sẽ giúp bạn nhớ từ lâu hơn. Nếu không biết nghĩa của một từ, hãy xem phần dịch nghĩa từ vựng ở trang 109.\r\n\r\nCác Wodoku được chia làm 3 cấp độ: dễ, tương đối khó và khó. Nếu có câu đố nào quá khó, hãy xem phần đáp án ở trang 94.'),
(15, '12 Chòm Sao Và Những Điều Chưa Nói\r\n', 'Herbert Schendl', 55000, 9, '“Lại đêm nữa, bà đơn phương vùi khát thèm bằng trắng đêm giã gạo. Những đêm trăng quê yên tĩnh trải rộng miên man dài. Những đêm giã gạo toát mồ hôi, gió vừa đủ lạnh thấm lỗ chân lông gời gợi. Những đêm không chịu nổi, bà ôm chặt cái thân cối giã gạo, òa khóc cho hả lòng hả dạ, cho bớt tủi nhục cô đơn. Trong đêm, cái khúc gỗ nhãn làm thân cối giã gạo được bào nhẵn vuông tròn mịn màng hiển hiện như một thân thể đàn ông. Tại sao không cho bà được yêu thương, được ôm ấp, được giận hờn”.\r\n“Lại đêm nữa, bà đơn phương vùi khát thèm bằng trắng đêm giã gạo. Những đêm trăng quê yên tĩnh trải rộng miên man dài. Những đêm giã gạo toát mồ hôi, gió vừa đủ lạnh thấm lỗ chân lông gời gợi. Những đêm không chịu nổi, bà ôm chặt cái thân cối giã gạo, òa khóc cho hả lòng hả dạ, cho bớt tủi nhục cô đơn. Trong đêm, cái khúc gỗ nhãn làm thân cối giã gạo được bào nhẵn vuông tròn mịn màng hiển hiện như một thân thể đàn ông. Tại sao không cho bà được yêu thương, được ôm ấp, được giận hờn”.'),
(16, 'Những Chuyến Phiêu Lưu Nhất Quả Đất - Hoàng Tử Ivan, Con Chim Lửa Và Con Sói Xám\r\n', '', 24500, 5, '12 chòm sao hay 12 cung hoàng đạo từ lâu vốn là đề tài được giới trẻ rất yêu thích. Ngoài sự chính xác được thừa nhận thì những điều thú vị xung quanh các chòm sao ấy cũng khiến độc giả phải bật cười và thích thú. Cuốn sách tranh 12 chòm sao và những điều chưa nói xoay quanh những mẩu chuyện nhỏ về cuộc sống, tình yêu của các chòm sao. Đó là chuyện tình yêu lúc đến và đi, là thái độ của từng chòm sao khi đối diện với những vấn đề quanh cuộc sống, là nhóm tính cách chưa-chắc-bạn-đã-biết của họ, hay là phản ứng đối với bộ phim Cô dâu 8 tuổi đang gây sốt vừa qua.\r\n12 chòm sao hay 12 cung hoàng đạo từ lâu vốn là đề tài được giới trẻ rất yêu thích. Ngoài sự chính xác được thừa nhận thì những điều thú vị xung quanh các chòm sao ấy cũng khiến độc giả phải bật cười và thích thú. Cuốn sách tranh 12 chòm sao và những điều chưa nói xoay quanh những mẩu chuyện nhỏ về cuộc sống, tình yêu của các chòm sao. Đó là chuyện tình yêu lúc đến và đi, là thái độ của từng chòm sao khi đối diện với những vấn đề quanh cuộc sống, là nhóm tính cách chưa-chắc-bạn-đã-biết của họ, hay là phản ứng đối với bộ phim Cô dâu 8 tuổi đang gây sốt vừa qua.'),
(17, 'Ngụ Ngôn Aesop (Nhã Nam)\r\n', 'Aesop', 67000, 13, 'Những chuyến phiêu lưu nhất quả đất sẽ đưa các đọc giả nhí đi khắp nơi, từ các vùng đất xa xôi, đến những thế giới kỳ lạ, rồi bay lên bầu trời và xuyên qua lòng đất; giúp các em có được trải nghiệm thú vị, bồi dưỡng trí tưởng tượng phong phú; bên cạnh đó sẽ hiểu hơn về lòng yêu thương, về tình cảm gia đình, bạn bè; giúp các em hiểu được lòng tin, sự chân thành; nhắc nhở các em về lòng tham, sự đố kỵ… Đó chính là những điều tuyệt vời để tâm hồn các em thêm sâu sắc và tươi mới.\r\n\r\nMột điểm cộng của bộ truyện là trò chơi “Cùng làm sách bỏ túi!” vô cùng hấp dẫn ở phần cuối truyện. Các bạn nhỏ sẽ được trực tiếp tham gia vào công việc tạo nên một cuốn sách bỏ túi cho riêng mình. Còn gì thú vị hơn!\r\n\r\nBộ truyện Những chuyến phiêu lưu nhất quả đất, món quà đặc biệt dành tặng cho những em bé luôn mỉm cười, và thích phiêu lưu, khám phá.'),
(18, '50 Điều Trường Học Không Dạy Bạn Và 20 Điều Cần Làm Trước Khi Rời Ghế Nhà Trường\r\n', 'Alphabooks', 89000, 4, 'Đứa trẻ nào cũng được dạy ở trường rằng, một đứa trẻ ngoan phải biết yêu thương mọi người; kính trọng, lễ phép với ông bà, cha mẹ và thầy cô giáo; hoà nhã, thân ái với bạn bè,… Một học sinh giỏi phải biết giải các bài toán khó, biết làm những bài văn hay, phải thuộc bài,… Nhưng không có trường lóp nào nói với chúng là “các em không hoàn hảo nhưng cũng không cần phải tỏ ra là người hoàn hảo”; chẳng có bài học nào rèn luyện cho chúng bản lĩnh để đối mặt với những bất công trong cuộc sống; cũng không có trang sách nào dạy chúng biết rằng “một lần bị bẽ mặt, các em sẽ trưởng thành hơn”… Vậy bọn trẻ phải học những điều đó từ đâu?\r\n\r\nThành công trong nhà trường, trở thành một học sinh, sinh viên giỏi không đồng nghĩa với thành công trong cuộc sống. Ngay cả Bill Gates - người đã bỏ dở chương trình đại học tại Harvard… cũng từng coi nhiều điều trong số đó là lời khuyên cho chính mình. 50 điều nói trong cuốn sách không chỉ viết cho bọn trẻ, mà còn dành cho các bậc phụ huynh và cả những người thầy. Ngay cả những người đã tốt ngiệp đại học, đã đi làm cũng thấy học hỏi được nhiều từ những lời khuyên này.\r\n\r\nHãy đọc cuốn sách để biết bạn đã Không đuợc học những gì trong nhà trường. Hãy đọc để trưởng thành hơn và thành công hơn.\r\n\r\n“Ra trường, việc đầu tiên tôi làm là bắt tay vào tìm một công việc. Nhưng mọi việc không đơn giản như tôi nghĩ. Có rất nhiều điều mới lạ của cuộc sống thực tế mà cả trường học và cha mẹ tôi chưa từng dạy cho tôi bao giờ. Nó khiến tôi lúng túng và loay hoay cho đến khi đọc cuốn 50 điều trường học không dạy bạn…”\r\n'),
(19, 'Ban Ki Moon - Hãy Học Như Kẻ Ngốc Và Ước Mơ Như Thiên Tài\r\n', 'Shin Woong Jin', 89000, 11, 'Từ khi còn là một cậu bé cho đến khi trở thành Tổng thư ký Liên Hợp Quốc, ông luôn là tấm gương học tập không ngừng nghỉ cho thế hệ trẻ noi theo. Sinh ra và lớn lên tại một ngôi làng hẻo lánh ở Hàn Quốc, với khả năng tập trung cao độ, sự đam mê bất tận với những điều mới lạ đặc biệt là niềm yêu thích học tiếng Anh từ nhỏ, ông đã chiến thắng trong cuộc thi hát tiếng Anh do Hội chữ thập đỏ Mỹ tổ chức. Tấm vé tham gia Chương trình thăm quan nước Mỹ dành cho học sinh sinh viên quốc tế - VISTA và cuộc gặp gỡ với Tổng thống Hoa Kỳ John F. Kennedy ở Washington D.C đã thổi bùng ước mơ trở thành nhà ngoại giao trong ông.\r\n\r\nTrong những tháng năm sau đó, bằng sự đam mê và nỗ lực không ngừng, ông đã trở thành niềm tự hào của người dân Hàn Quốc, người đứng đầu của một trong những tổ chức quyền lực nhất thế giới và hơn hết là người soi đường tin cậy cho ước mơ của biết bao thế hệ trẻ.Cuốn sách Ban Ki Moon - Hãy học như kẻ ngốc và mơ ước như thiên tài ra đời mới đó đã 5 năm. Trong thời gian đó, Tổng thư ký Liên Hợp Quốc Ban Ki Moon đãbôn ba khắp nơi vì hòa bình thế giới và vì những vấn đề mang tính toàn cầu như biến đổi khí hậu, vấn nạn đói nghèo,… Và bằng những nỗ lực đó, ông được tín nhiệm tái đắc cử vị trí này nhiệm kỳ thứ hai.\r\n\r\nHơn nữa, thế hệ trẻ Hàn Quốc đã không do dự bình chọn Ban Ki Moon là nhân vật được tôn kính nhất tại đất nước này.'),
(20, 'Nhân Quả Không Miễn Trừ Ai\r\n', 'Nguyễn Chu Phác', 100000, 6, 'Tôi không bao giờ tin vào những chuyện bói toán, dự đoán tương lai, xem ngày giờ làm chuyện AB, rồi nhương sao giải hạn kể cả chuyện đốt giấy vàng mã, thờ cúng thần tài. Chỉ bởi tôi là một Phật tử, hiểu được nguyên lý nhân quả và nghiệp báo họa phước của đời người không thừa trừ một ai:\r\n\r\n          - Gieo nhân lành gặt quả thiện, và chí ít làm giảm đi phần nghiệp ác đã gieo (từ đời trước và ở đời này)\r\n\r\n          - Gieo nhân ác sẽ lãnh lấy ác nghiệp, và làm giảm đi những thiện nghiệp, phước lành đáng được thọ nhận (do đời trước hoặc ngay trong đời này đã gieo).\r\n\r\nĐiều này cũng lý giải cho nhiều đại gia, quan chức thụ hưởng nhiều quả lành nhưng thay vì tiếp tục gieo nhân thiện để tích thêm phước báo lại sa đà vào dục lạc đồi trụy, làm nhiều hành vi lợi mình mà hại người, dẫm đạp lên thiên hạ để củng cố quyền lực, tiền tài nên sớm mất đi phần phước. Màn "phước" bị mỏng dần  và mất hết thì họa sẽ đến và đến liên tục bởi những nghiệp ác đã gieo đời trước và đời này. "Quả báo nhãn tiền" là vậy đấy.\r\n\r\nTôi nhìn vào mọi sự kiện trong đời rất đơn giản như một phép cộng trừ giữa nhân quả nghiệp báo và phước họa hiện tiền. Cho nên tôi không tin vào thần tài, chả cần xem biết ngày giờ AB, cũng không cần nhương sao giải hạn, đốt giấy vàng mã làm gì. Mọi thứ đều tùy duyên và do mình tác tạo. "Tự tác tự thọ" là vậy đấy.\r\n\r\nKhông lý gì mà bạn gieo nhiều ác nghiệp hại bao chúng sinh rồi đi nhương sao giải hạn để giảm đi cái họa trong tương lai theo mệnh số mỗi năm (được dự báo rất chi là chung chung cho bàng dân thiên hạ trong những tập sách tử vi "tạp nham" được bày bán khắp nơi, từ nhà đến chợ, từ chợ đến Chùa tự).\r\n\r\nHoặc bị bệnh hoạn trầm kha, nghe theo ai đó rồi giết hại đi nhiều sinh mạng của các loài vật kể cả loài quý hiếm  để "khẩu dụng" chữa bệnh cho mình. Tội lỗi, tội lỗi! Làm sao chữa được theo kiểu đó.\r\n\r\nRất nhiều đại gia, quan chức đã "ngã ngựa", thời gian huy hoàng của họ không quá 10 năm. Cũng bởi lòng tham không đáy, không biết "tri túc tiện túc", lúc nào họ cũng thấy thiếu tiền, thiếu quyền, nên cứ lao vô kiếm, bằng tham nhũng, tham ô, vô hình trung đã tích "ác" quá nhiều.\r\n\r\nCâu chuyện của Dương Chí Dũng là 1 điển hình. Ông ta trụ ở đơn vị nào là tanh bành đơn vị đó, rồi chạy chức, chạy quyền lên chỗ cao hơn, ngon ăn hơn, hành vi của ông ta đã trực tiếp hại cả một tập đoàn và hàng ngàn CNVC, người lao động ở đó thất điên bát đảo. Bao nhiêu năm của một DCD huy hoàng, ăn trên ngồi trước, rồi nghe lời "ông anh" mật báo trốn chạy như chuộc lũi hơn nửa năm trời cũng phải vào nhà đá, kéo theo hàng loạt vị dính vòng lao lý. "Đến chết tôi cũng không nhận tội tham ô", bởi tôi tham nhũng chứ đâu có tham ô. Nếu không sao hết lần này đến lần khác biếu "USD" cho ông anh để nhờ vả lấp liếm những chuyện tày đình của ông ta. Đây cũng là chiêu mà ông  dùng để leo lên ghế "Tổng". Tất nhiên không riêng gì ông ta.\r\n\r\nHà rầm giới chức khác cũng mua quan bán tước kiểu này, mà vài năm trở lại đây, không thèm chơi tiền Việt, biếu toàn là "Đô xanh" không hà. Đến khi có chức quyền bèn tìm cách moi lại của thiên hạ để bù đắp những gì đã mất, đủ bản lĩnh thì giấu được tội (nhưng tránh trời không khỏi nắng, lại bị kẻ khác lừa hết, của thiên trả địa), dật dờ thiếu kinh nghiệm thì từ chết đến bị thương, lần lượt vào nhà đá.\r\n\r\nKhông ít người trong số đó, hằng năm cũng làm từ thiện, cũng cúng dường phẩm vật cho Chùa, Tăng sư mong giảm bớt nghiệp ác theo kiểu "nhuơng sao giải hạn", cầu an, cầu phước, cầu lộc... Có biết đâu, cúng dường Tăng Ni bằng của bất tịnh nào đem lại phước báo cho bản thân.\r\n\r\nThôi, tốt nhất là chúng ta hãy thực hiện những hành vi lợi mình lợi người, tuyệt đối tránh kiểu hành xử "lợi mình, hại người" như VietinBank đã làm trong vụ án Huy'),
(21, 'Chuyến Du Hành Qua Vòng Tròn Hoàng Đạo 2016 ', 'Lovedia', 37000, 6, 'Song Tử vốn là một cơn gió tự do, nhưng trong năm tới đây, sẽ có khá nhiều thứ níu chân bạn đấy. Đây sẽ là một năm đầy khác biệt so với các năm trước đó, êm đềm hơn trong cuộc sống riêng, đầu tư nhiều hơn cho các mối quan hệ thân thiết và tận hưởng một câu chuyện lãng mạn...\r\n\r\nBạn có biết, sao Mộc là một ông bác tốt bụng trao cho bạn tin tốt lành và may mắn, sao Thổ là cụ non khó tính sẽ bắt bạn dọn dẹp từng hạt bụi, sao Kim mang đến tình yêu lãng mạn, sao Thủy sẽ kéo bạn đi chơi suốt ngày, nhưng lúc trái tính trái nết sẽ phá ngôi nhà của bạn tanh bành?\r\n\r\nHãy cùng quan sát bầu trời và khám phá Chuyến du hành qua vòng tròn Hoàng đạo của các hành tinh trong năm 2016 để xem những “vị khách” này sẽ “làm gì” bạn trong năm nay nhé!'),
(22, 'Nhà Giả Kim Phiên Bản Mới 2015', 'Jack Canfield', 80000, 3, 'Bạn là năng lượng\r\n\r\nTinh khiết và đơn giản. Bạn được tạo ra từ những thứ giống như mặt trời, mặt trăng và những vì sao. Bạn là tập hợp của một luồng sinh khí có trí tuệ, biết đi, biết nói và tồn tại dưới dạng một cơ thể con người. Bạn được tạo nên từ những tế bào, những tế bào này lại được tạo ra từ các nguyên tử, và các nguyên tử này được tạo ra từ những hạt nhỏ hơn nguyên tử. Những hạt nhỏ hơn nguyên tử đó là gì? Xin thưa, đó chính là NĂNG LƯỢNG!\r\n\r\nVạn vật đều là năng lượng.\r\n\r\nNăng lượng không được tạo ra, cũng không thể phá hủy.\r\n\r\nTự nó đã mang tính nhân quả.\r\n\r\nNó hiện diện như nhau ở mọi lúc, mọi nơi.\r\n\r\nNăng lượng chuyển động vĩnh hằng và không bao giờ ngừng nghỉ.\r\n\r\nNó chỉ chuyển hóa từ dạng này sang dạng khác, luôn luôn như vậy.\r\n\r\nSUY NGHĨ SINH RA NĂNG LƯỢNG.\r\n\r\nTrong vũ trụ này không có gì là thừa cả. Mỗi người có mặt trên cõi đời này vì họ cần lấp đầy một khoảng trống nào đó, và tất cả các mảnh nhỏ đều phải vừa khít với chỗ của nó trong tấm xếp hình khổng lồ của vũ trụ.'),
(23, 'Vỡ Vụn Tuổi 20', 'Gari', 43000, 9, 'Khi con người ta trải qua quá nhiều chuyện, thì rốt cuộc cũng giống như không có gì. Ở tuổi 20, người ta không phải là đã lớn, nhưng cũng chẳng còn dễ mỉm cười ngây thơ hay dỗi hờn. Khủng hoảng tuổi 20, ta không biết được rằng liệu cuối cùng ta có gì trong tay, ta thực sự mong muốn điều gì, và đích đến của ta là ở đâu. Đôi lúc, ta cảm thấy cuộc sống thật ồn ào, chỉ muốn chui rúc một góc nhỏ nào đó tạm gọi là “chốn bình yên” để trở về. Thế nhưng, cũng có lúc ta thấy bản thân mình thật cô đơn, cần lắm một ai đó để dựa vào. Để rồi đến khi bế tắc, ta lại tự an ủi mình với câu trả lời quen thuộc: “Đại khái là thế nào cũng được! Việc gì đến sẽ đến”.\r\n\r\nThế giới có rất nhiều màu, nhưng khi có vô vàn màu sắc chồng chất, thì lại trở thành màu đen nguyên thủy đờ đẫn. Cuộc sống dài rộng, thời gian vô tình, nhiều khi thấy bản thân chông chênh giữa một đống bề bộn đến lạ. Tôi hy vọng tập tản văn này sẽ là cuốn sách “gối đầu giường” của bạn, là một người bạn đồng hành để chứng minh rằng bạn không hề cô đơn trong chuyến hành trình cuộc sống của riêng mình. Đừng chôn giấu và chịu đựng tất cả những âu lo, sầu muộn hay lo lắng. Đừng tự hành hạ chính mình và đánh mất đi bản thân. Đừng im lặng. Bởi thực chất, sự im lặng không phải là thói quen hay sở thích, mà là sự thỏa hiệp với chính mình. Chỉ cần bạn biết rằng, những bí mật của tuổi 20 mà bạn đang có, những vấn đề mà bạn đang nghĩ, cũng từng có người giống như bạn, nhưng họ vẫn cố gắng vượt qua và lớn lên từng ngày.\r\n\r\nHãy nhìn lại những “thú nhận” tuổi 20 đầy sóng gió của tôi - và cả những con người lướt ngang đời tôi, biết đâu bạn sẽ tìm thấy đâu đó quẩn quanh hình ảnh của chính mình. Để rồi đến khi cảm xúc vỡ vụn ra, sẽ là những khoảng trống của bầu trời hy vọng xanh hun hút…\r\n\r\n"Có nhiều lúc, ta tự thấy bản thân mình vẫn chỉ là một đứa trẻ to xác. Những gì ở bên ngoài, suy cho cùng, cũng chỉ là sự biến đổi ở thể xác, còn tâm hồn, vốn dĩ chẳng thể thay đổi. Thôi thì, nếu như đã không thể xoa dịu quá khứ, thì hãy để cuộc sống hiện tại là một món quà. Trong cuộc sống, có những người suốt ngày chỉ chăm chăm nhìn vào quá khứ tươi đẹp mà không biết trân trọng hiện tại - với những thứ mà họ đang có. Họ không nhận ra một điều rằng, thứ khởi đầu chưa chắc đã là thứ kết thúc. B'),
(24, 'Chân Tướng Đàn Ông', 'Tiết Hảo Đại', 67000, 6, 'Trong cuốn sách này không có những lý luận cao thâm, cũng chẳng để vạch trần chuyện gì, càng không phải cố tình hạ thấp đàn ông để làm vừa lòng một vài người nào đó... Nó đơn giản chỉ là mượn trải nghiệm của một số người để nói rõ một phần nào đó về đàn ông.\r\n\r\nChân tướng chưa hẳn là tốt đẹp nhưng cũng không hoàn toàn là xấu xa. Sinh vật được gọi là “đàn ông” cũng có những mặt rất đáng yêu. Hy vọng cuốn sách nhỏ viết về những đặc điểm và sự giống nhau của đàn ông này sẽ giúp cho các bạn gái còn đang mơ hồ, mờ mịt có thể tìm hiểu được lời giải cho chính câu hỏi trên:\r\n\r\n"Người đàn ông như thế nào mới thích hợp chung sống với bạn đến hết cuộc đời này?"'),
(25, 'Vùng Trời Hư Cấu', 'Phan Thành Trí', 57900, 14, 'Nhưng đừng lo, trước khi bạn kịp định hình câu trả lời, sự “bựa” đã thống trị thế giới xong xuôi rồi!\r\n\r\nBạn đã từng bị deadline kề kề bên cổ?\r\n\r\nBạn đã từng thiếu tiền?\r\n\r\nHay tệ hơn là đã từng bị bắt nạt?\r\n\r\nNhưng bạn vẫn thấy những điều đó là bình thường?\r\n\r\nVậy hãy để sự “hư cấu” ngập tràn trong từng trang truyện giúp bạn “kịch tính hóa” mọi thứ lên theo cách mà bạn không bao giờ nghĩ tới nhé!\r\n\r\nĐược mang đến bạn từ tác giả của fanpage Bựa - fanpage “bùng cháy” nhất Việt Nam với hàng trăm nghìn người theo dõi, hàng chục nghìn lượt share và comment mỗi ngày, Vùng trời hư cấu là một bộ truyện tranh thuộc thể loại bi - hài - tâm lý - hình sự khó giải thích, sẽ mang đến cho bạn những tiếng cười khó hiểu nhất trước giờ!\r\n\r\nBên cạnh đó, phong cách dàn trang theo giao diện mạng xã hội cực kỳ độc đáo và thời thượng lần đầu tiên xuất hiện trên sách Skycomics chắc chắn sẽ khiến bạn không thể cưỡng lại được sức hấp dẫn của Vùng Trời Hư Cấu!\r\n\r\nHãy để cuộc sống của bạn ngập tràn trong Hư Cấu cùng tác giả Thành Trí trong mùa thu này nhé!'),
(26, 'Lão Kẹo Gôm Và Cây Anh Đào (Tái Bản 2014)', 'Andy Stanton', 109000, 19, '“Tổ Tiên đã quay lại rồi!”\r\n\r\nVào một buổi sáng đầu xuân ngây thơ đến nao lòng, Lão Bà Bà vừa chạy như vịt vừa thét lên cái tin động trời ấy, khuấy lên một mớ hỗn độn ì xèo và lôi cả thị trấn Lamonic Bibber bình yên vào một cơn cuồng loạn đến nỗi suýt nữa đánh mất cả linh hồn.\r\n\r\nKinh khủng đến thế kia à!\r\n\r\nVậy Tổ Tiên là ai hay cái gì mà đáng sợ vậy?\r\n\r\nBạn hãy chuẩn bị tinh thần để cười chảy nước mắt (trong run rẩy), khi cùng Polly can đảm và Alan Taylor, chú bánh gừng tốt bụng, dấn thân vào khu rừng u tối và khám phá ra bí mật bên trong cây anh đào cổ thụ! (Bật mí thêm: lần này ông Thứ Sáu không tham gia lực lượng giải cứu được, vì chính ông cũng đang bị cuốn vào cơn mê muội phủ bóng u ám lên cả thị trấn kìa).'),
(27, 'Trọn Đời Bên Nhau', 'Mặc Bảo Phi Bảo', 60000, 27, '"Em vẫn muốn được tiếp tục yêu anh."\r\n\r\nCố Bình Sinh đặt một tay lên trên mặt kính, đầu khẽ dựa vào tường kính, nhìn thẳng vào mắt cô và trả lời: “I never left.”\r\n\r\n"I never left - Anh chưa bao giờ rời đi..."\r\n\r\nTrong thế giới đã mất đi âm thanh của mình, anh cũng dần lãng quên giọng nói của từng người thân thuộc, nhưng duy chỉ có giọng nói của cô năm đó là anh vẫn nhớ mãi. Đó dường như là âm thanh duy nhất vang lên trong anh mỗi khi nhìn thấy cô, nói chuyện với cô.\r\n\r\nCô là một cô gái giấu trong trong lòng mình nhiều bí mật. Cô không nói với ai về gia cảnh của bản thân mình. Bố mẹ ly hôn, một người bố mê mẩm với cổ phiếu luôn ngập trong nợ nần và một người bà đã già yếu luôn cần cô chăm sóc. Cô đã cố gắng kiên cường, tự gánh vác tất cả qua bốn năm đại học cho đến khi anh xuất hiện. Anh cứ thản nhiên mà xâm nhập vào cuộc sống của cô và đỡ lấy trách nhiệm đè nặng trên vai cô bao lâu nay. Để rồi, không biết từ thưở nào, cô đã yêu người đàn ông dịu dàng có đôi mắt sâu hun hút ấy.\r\n\r\nhttp://tikicdn.com/media/catalog/product/t/r/tron-doi-ben-nhau-mac-bao-phi-bao.jpg\r\n\r\nHọ đến với nhau như một điều tất nhiên.\r\n\r\nHọ thuộc về nhau như khi hai mảnh ghép vừa khít và duy nhất trên thế giới này.\r\n\r\nThế nhưng, khi trái tim người này chỉ tồn tại duy nhất người kia, điều người này lo lắng nhất chỉ là hạnh phúc của người kia, thì vì người này, người kia cũng có thể hy sinh mọi thứ, cho dù là tình yêu của chính mình…'),
(28, 'Khi Yêu Cần Nhiều Dũng Cảm', 'Chetah Bhagat', 89000, 17, 'Trên thế giới này, những cuộc hôn nhân dựa trên tình yêu luôn theo một qui trình tuần tự: chàng trai yêu cô gái, cô gái yêu chàng trai, họ kết hôn.\r\n\r\nTuy nhiên, ở Ấn Độ, có thêm vài bước nữa: chàng trai yêu cô gái, gia đình cô gái thích chàng trai, gia đình chàng trai thích cô gái, họ hàng cô gái thích họ hàng chàng trai, họ hàng chàng trai thích họ hàng cô gái, chàng trai và cô gái vẫn yêu nhau, họ kết hôn.Đó chính là câu chuyện của Krish và Ananya. Trai tài, gái sắc. Là đôi bạn đồng chí hướng trong những tháng năm học MBA tại ngôi trường danh giá hàng đầu Ấn Độ, họ thậm chí còn chờ một lễ cưới rực rỡ. Nhưng trăm núi nghìn sông còn dễ vượt qua hơn so với sự phản đối của hai bên gia đình. Hai người phải làm thế nào đây, khi nổi loạn thì dễ, thuyết phục người khác mới là điều khó.\r\n\r\nThành danh với tiểu thuyết Ba chàng ngốc, lần này Chetan Bhagat mang đến một tác phẩm gần như tự truyện của mình. Khi Yêu Cần Nhiều Dũng Cảm, cuốn tiểu thuyết dí dỏm về cuộc hôn nhân liên cộng đồng tại Ấn Độ hiện đại, đã được chuyển thể thành phim điện ảnh, với những ca ngợi của giới phê bình và công phá mãnh liệt doanh số phòng vé.'),
(29, 'Những Điều Trường Harvard Không Dạy Bạn', 'Mark H. Mccormack', 132000, 11, 'Với lối viết thẳng thắn và mạnh mẽ, Những Điều Trường Harvard Không Dạy Bạn cung cấp nhiều kinh nghiệm thực tế về:\r\n\r\nCách thức thấu hiểu một con người\r\n\r\nNhững bí ẩn của một cuộc đàm phán\r\n\r\nCách điều hành và tham dự một cuộc họp\r\n\r\nBiến sự giận dữ của đối tác thành cơ hội\r\n\r\nĐón nhận những thách thức\r\n\r\nNhạy bén để biết vận may\r\n\r\nĐây thật sự là cuốn sách dành cho tất cả những ai thực sự muốn  thành công trong giới kinh doanh đầy thách thức.');
INSERT INTO `Book` (`BID`, `Name_Book`, `Author`, `Price`, `Quantity`, `Content`) VALUES
(30, '13 Nguyên Tắc Nghĩ Giàu Làm Giàu - Think And Grow Rich', 'Napoleon Hill', 120000, 17, '13 Nguyên Tắc Nghĩ Giàu Làm Giàu là cuốn sách “chỉ dẫn” duy nhất chỉ ra những nguồn lực bạn phải có để thành công. Cuốn sách sẽ giúp bạn trở nên giàu có, làm giàu thêm cho cuộc sống của bạn trên tất cả các phương diện của cuộc sống chứ không chỉ về tài chính và vật chất. Những ý tưởng trong cuốn sách Think and Grow rich - 13 nguyên tắc nghĩ giàu, làm giàu bắt nguồn từ những động lực tốt đẹp: “Thành công cá nhân” và “Quan điểm suy nghĩ tích cực”.\r\n\r\nCuốn sách chứa đựng nhiều hơn những gì mà cuốn sách giải thích về sức mạnh của những nguyên tắc. Phần hấp dẫn nhất của cuốn sách chính là những điều phi thường, những thông điệp trong cuốn sách được viết ra từ rất lâu nhưng vẫn mang tính “thời đại”. Ông đã bàn về những quan niệm như quản lý nhóm, dịch vụ chăm sóc khách hàng hoàn hảo, những công cụ hữu hình, trí tuệ tập thể và mục tiêu cần được viết ra trước khi hành động.\r\n\r\nNhững lời khen tặng\r\n\r\n“Think and grow rich – 13 nguyên tắc nghĩ giàu, làm giàu sẽ luôn là cuốn sách giá trị và nó sẽ còn tiếp tục làm thay đổi cuộc sống của nhiều người. Đó thực sự là sức mạnh giúp chúng ta biến đổi những giấc mơ và hoài bão thành hiện thực. Cuốn sách đã ảnh hưởng đến tôi như vậy và sẽ mãi là cuốn sách gối đầu giường số một của tôi.”\r\n\r\n(Don L.Price, diễn giả chuyên nghiệp, tác giả, chuyên viên marketing và bán hàng đồng thời là người cung cấp các giải pháp thay đổi tích cực)\r\n\r\n“Think and Grow Rich - 13 nguyên tắc nghĩ giàu, làm giàu là cuốn sách có ảnh hưởng rất lớn đến cuộc sống của tôi. Công ty Contours Express của tôi sẽ không thể tồn tại nếu không có cuốn sách này. Và bây giờ, sau 7 năm và 15 lần đọc đi đọc lại cuốn sách, chúng tôi đã có hơn 350 trung tâm chuyển phát nhanh. Mỗi lần đọc nó, tôi vẫn luôn tìm ra được những điều hữu ích cho bản thân và công việc của mình. Không còn nghi ngờ gì nữa, đây quả thực là một cuốn sách viết về kinh doanh tuyệt vời nhất mà tôi từng biết.”\r\n\r\n(Daren Carter, người sáng lập Công ty chuyển phát nhanh đa quốc gia Contours Express hoạt động tại sáu quốc gia trên thế giới)\r\n\r\n“Ấn bản gốc của Tiến sĩ Hill đã làm thay đổi cả thế kỷ XX của chúng ta. Ấn bản mới sau khi chỉnh sửa này cũng sẽ thay đổi cả thế kỷ XXI này. Không có tác giả nào có thể có lối suy nghĩ như một người lãnh đạo, một nhà cố vấn trong việc phát triển và thúc đẩy khả năng của mỗi cá nhân như Napoleon Hill. Công trình này đã nâng ông lên một tầm cao mới, có lẽ còn hơn cả sự vĩ đại.”\r\n\r\n(Bill Brooks, tác giả cuốn sách The New Science of Selling and Persuasion)\r\n\r\n“Tôi từng nghĩ rằng Think and Grow Rich - 13 nguyên tắc nghĩ giàu, làm giàu là một cuốn sách kinh điển và không cần phải cải thiện gì thêm nữa. Tôi đã nhầm, tôi chắc rằng Tiến sĩ Hill sẽ rất vui mừng khi thấy tác phẩm của ông được tôn vinh hơn với ấn bản vô cùng nổi bật này.”\r\n\r\n(Wally Amos, người sáng lập ra hãng Famous Amos Chocolate Chip Cookies, tác giả cuốn sách The Cookies Never Crumbles, Inspirational Recipes for Everyday Living)\r\n\r\n"Tôi đã đọc Think and Grow Rich - 13 nguyên tắc nghĩ giàu, làm giàu cách đây nhiều năm và cuốn sách đã giúp tôi trở thành nhà kinh doanh bán lẻ lớn nhất thế giới. Bạn cần đọc nó nếu bạn muốn có một “chỗ đứng” trong cuộc đời này. Tôi biết bạn sẽ yêu nó, vì tôi cũng vậy. Cảm ơn ngài, Napoleon Hill!”\r\n\r\n(Joe Girard, nhà kinh doanh bán lẻ số một thế giới, được ghi danh vào cuốn The Guiness Book of World Records)');

-- --------------------------------------------------------

--
-- Table structure for table `Cart`
--

CREATE TABLE IF NOT EXISTS `Cart` (
  `UID` int(11) NOT NULL,
  `BID` int(11) NOT NULL,
  `Total` int(11) NOT NULL,
  PRIMARY KEY (`UID`,`BID`),
  KEY `BID` (`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Cart`
--

INSERT INTO `Cart` (`UID`, `BID`, `Total`) VALUES
(195842465, 1, 0),
(195842465, 2, 0),
(195842465, 3, 0),
(195875456, 5, 0);

-- --------------------------------------------------------

--
-- Table structure for table `Distribute`
--

CREATE TABLE IF NOT EXISTS `Distribute` (
  `PID` int(11) NOT NULL,
  `Sort` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'Ten loai',
  PRIMARY KEY (`PID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Distribute`
--

INSERT INTO `Distribute` (`PID`, `Sort`) VALUES
(1, 'Sách Tiếng Anh'),
(2, 'Sách Văn Học - Tiểu thuyết'),
(3, 'Sách Kinh Tế'),
(4, 'Sách Chuyên Ngành'),
(5, 'Sách Kỹ Năng sống - Nghệ thuật sống'),
(6, 'Sách Giáo Khoa - Tham Khảo'),
(7, 'Sách Thiếu Nhi'),
(8, 'Sách Thường Thức - Đời Sống'),
(9, 'Sách Văn Hóa - Nghệ thuật - Du Lịch'),
(10, 'Sách Ngoại Ngữ - Từ Điển');

-- --------------------------------------------------------

--
-- Table structure for table `Favorite`
--

CREATE TABLE IF NOT EXISTS `Favorite` (
  `UID` int(11) NOT NULL,
  `BID` int(11) NOT NULL,
  PRIMARY KEY (`UID`,`BID`),
  KEY `BID` (`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Favorite`
--

INSERT INTO `Favorite` (`UID`, `BID`) VALUES
(195842465, 1),
(195842465, 2),
(195842465, 3),
(1672483929, 3),
(195875465, 4),
(195875456, 5),
(1672483929, 6),
(195875465, 7),
(195875456, 8),
(195875456, 9),
(195875656, 10);

-- --------------------------------------------------------

--
-- Table structure for table `Genre_Book`
--

CREATE TABLE IF NOT EXISTS `Genre_Book` (
  `PID` int(11) NOT NULL,
  `BID` int(11) NOT NULL,
  PRIMARY KEY (`BID`,`PID`),
  KEY `PID` (`PID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Genre_Book`
--

INSERT INTO `Genre_Book` (`PID`, `BID`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 6),
(2, 1),
(2, 3),
(2, 9),
(2, 10),
(3, 10),
(3, 15),
(3, 19),
(4, 11),
(4, 13),
(4, 15),
(5, 11),
(5, 12),
(5, 14),
(5, 17),
(6, 7),
(6, 8),
(6, 14),
(7, 9),
(7, 10),
(7, 18),
(8, 15),
(8, 18),
(8, 19),
(9, 15),
(10, 12),
(10, 14),
(10, 15);

-- --------------------------------------------------------

--
-- Table structure for table `Order_Book`
--

CREATE TABLE IF NOT EXISTS `Order_Book` (
  `OID` int(11) NOT NULL,
  `BID` int(11) NOT NULL,
  `Quantity` int(11) DEFAULT '0',
  PRIMARY KEY (`OID`),
  KEY `BID` (`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Order_User`
--

CREATE TABLE IF NOT EXISTS `Order_User` (
  `OID` int(11) NOT NULL,
  `UID` int(11) NOT NULL,
  `Payment` int(11) DEFAULT '0',
  PRIMARY KEY (`OID`),
  KEY `UID` (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Publisher`
--

CREATE TABLE IF NOT EXISTS `Publisher` (
  `BID` int(11) NOT NULL,
  `Name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Publisher`
--

INSERT INTO `Publisher` (`BID`, `Name`) VALUES
(1, 'NXB Văn Học'),
(2, 'NXB Văn Học'),
(3, 'NXB Lao Động'),
(4, 'NXB Lao Động'),
(5, 'NXB Trẻ'),
(6, 'NXB Mỹ Thuật'),
(7, 'NXB Văn Hóa - Văn Nghệ'),
(8, 'NXB Văn Học'),
(9, 'NXB Lao Động Xã Hội'),
(10, 'NXB Lao Động'),
(11, 'NXB Hồng Đức'),
(12, 'NXB Hồng Đức'),
(13, 'NXB Tổng hợp TP.HCM'),
(14, 'NXB Hội Nhà Văn'),
(15, 'NXB Dân Trí'),
(16, 'NXB Dân Trí'),
(17, 'NXB Văn Học'),
(18, 'NXB Lao Động Xã Hội'),
(19, 'NXB Thế Giới'),
(20, 'NXB Hội Nhà Văn'),
(21, 'NXB Tri thức\r\n'),
(22, 'NXB Văn Học\r\n'),
(23, 'NXB Văn Học\r\n'),
(24, 'NXB Trẻ'),
(25, 'NXB Dân Trí'),
(26, 'NXB Mỹ Thuật'),
(27, 'NXB Văn Hóa - Văn Nghệ'),
(28, 'NXB Văn Hóa - Văn Nghệ'),
(29, 'NXB Tổng hợp TP.HCM'),
(30, 'NXB Thế Giới');

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `UID` int(11) NOT NULL COMMENT 'Ten dang nhap',
  `Password` varchar(40) NOT NULL,
  `Name` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Address` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`UID`, `Password`, `Name`, `Address`) VALUES
(195842465, '1225560', 'Phạm Ngọc Thạch', 'Hà Nội'),
(195875456, '124430', 'Phạm Việt Thanh', 'Nghệ An'),
(195875465, '124460', 'Phạm Ngọc Thành', 'Hà Nội'),
(195875656, '124430', 'Nguyễn Việt Anh', 'Nam Định'),
(1672483929, '1234567', 'Nguyễn Văn Quang', 'Thanh Hóa');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Cart`
--
ALTER TABLE `Cart`
  ADD CONSTRAINT `Cart_ibfk_1` FOREIGN KEY (`UID`) REFERENCES `User` (`UID`),
  ADD CONSTRAINT `Cart_ibfk_2` FOREIGN KEY (`BID`) REFERENCES `Book` (`BID`);

--
-- Constraints for table `Favorite`
--
ALTER TABLE `Favorite`
  ADD CONSTRAINT `Favorite_ibfk_1` FOREIGN KEY (`UID`) REFERENCES `User` (`UID`),
  ADD CONSTRAINT `Favorite_ibfk_2` FOREIGN KEY (`BID`) REFERENCES `Book` (`BID`);

--
-- Constraints for table `Genre_Book`
--
ALTER TABLE `Genre_Book`
  ADD CONSTRAINT `Genre_Book_ibfk_1` FOREIGN KEY (`BID`) REFERENCES `Book` (`BID`),
  ADD CONSTRAINT `Genre_Book_ibfk_2` FOREIGN KEY (`PID`) REFERENCES `Distribute` (`PID`);

--
-- Constraints for table `Order_Book`
--
ALTER TABLE `Order_Book`
  ADD CONSTRAINT `Order_Book_ibfk_1` FOREIGN KEY (`OID`) REFERENCES `Order_User` (`OID`),
  ADD CONSTRAINT `Order_Book_ibfk_2` FOREIGN KEY (`BID`) REFERENCES `Book` (`BID`);

--
-- Constraints for table `Order_User`
--
ALTER TABLE `Order_User`
  ADD CONSTRAINT `Order_User_ibfk_1` FOREIGN KEY (`UID`) REFERENCES `User` (`UID`);

--
-- Constraints for table `Publisher`
--
ALTER TABLE `Publisher`
  ADD CONSTRAINT `Publisher_ibfk_1` FOREIGN KEY (`BID`) REFERENCES `Book` (`BID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
