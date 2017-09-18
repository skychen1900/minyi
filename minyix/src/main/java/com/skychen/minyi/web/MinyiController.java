package com.skychen.minyi.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skychen.minyi.repository.Minyi;
import com.skychen.minyi.service.MinyiService;

@Controller
public class MinyiController {
  final static Logger logger = LoggerFactory.getLogger(MinyiController.class);

  @Autowired
  MinyiService minyiService;

  @Autowired
  MessageSource msg;

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    sdf.setLenient(false);
    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
  }

  @RequestMapping(value = "/minyi", method = RequestMethod.GET)
  public String index(Model model, Pageable pageable) {
    logger.debug("请求>>index");
    Page<Minyi> minyiPage = minyiService.getAllMinyi(pageable);
    PageWrapper<Minyi> page = new PageWrapper<Minyi>(minyiPage, "/minyi");
    if (minyiPage.getTotalPages() == 0) {
      String message = msg.getMessage("minyi.list.empty", null, Locale.JAPAN);
      model.addAttribute("emptyMessage", message);
    }
    model.addAttribute("page", page);
    model.addAttribute("list", page.getContent());
    modelDump(model, "index");
    return "Minyi/index";
  }

  @RequestMapping(value = "/minyi/{id}", method = RequestMethod.GET)
  public ModelAndView detail(@PathVariable Integer id) {
    logger.debug("请求>>详细 id={}",id);
    ModelAndView mv = new ModelAndView();
    mv.setViewName("Minyi/detail");
    Minyi minyi = minyiService.getMinyi(id);
    mv.addObject("minyi", minyi);
    return mv;
  }

  @RequestMapping(value = "/minyi/search", method = RequestMethod.GET)
  public ModelAndView search(@RequestParam String keyword, Pageable pageable) {
    logger.debug("Minyi + search");
    ModelAndView mv = new ModelAndView();
    mv.setViewName("Minyi/index");
    if (StringUtils.isNotEmpty(keyword)) {
    	  Page<Minyi> minyiPage = minyiService.findMinyis(pageable, keyword);
    	  PageWrapper<Minyi> page = new PageWrapper<Minyi>(minyiPage, "/minyi/search");
		  if (minyiPage.getTotalPages() == 0) {
		    String message = msg.getMessage("minyi.list.empty", null, Locale.JAPAN);
		    mv.addObject("emptyMessage", message);
		  }
		  mv.addObject("page", page);
		  mv.addObject("list", page.getContent());
    }
    return mv;
  }

//  @RequestMapping(value = "/minyi/create", method = RequestMethod.GET)
//  public String create(MinyiForm form, Model model) {
//    logger.debug("Minyi + create");
//    List<Prefecture> pref = prefectureRepository.findAll();
//    model.addAttribute("pref", pref);
//    modelDump(model, "create");
//    return "Minyi/create";
//  }
//
//  @RequestMapping(value = "/minyi/save", method = RequestMethod.POST)
//  public String save(@Validated @ModelAttribute MinyiForm form, BindingResult result, Model model) {
//    logger.debug("Minyi + save");
//    if (result.hasErrors()) {
//      String message = msg.getMessage("minyi.validation.error", null, Locale.JAPAN);
//      model.addAttribute("errorMessage", message);
//      return create(form, model);
//    }
//    Minyi minyi = convert(form);
//    logger.debug("minyi:{}", minyi.toString());
//    minyi = minyiRepository.saveAndFlush(minyi);
//    modelDump(model, "save");
//    return "redirect:/minyi/" + minyi.getId().toString();
//  }
//
//  @RequestMapping(value = "/minyi/delete/{id}", method = RequestMethod.GET)
//  public String delete(@PathVariable Integer id, RedirectAttributes attributes, Model model) {
//    logger.debug("Minyi + delete");
//    minyiRepository.delete(id);
//    attributes.addFlashAttribute("deleteMessage", "delete ID:" + id);
//    return "redirect:/minyi";
//  }
//
//  /**
//   * convert form to model.
//   */
//  private Minyi convert(MinyiForm form) {
//    Minyi minyi = new Minyi();
//    minyi.setName(form.getName());
//    if (StringUtils.isNotEmpty(form.getHeight())) {
//      minyi.setHeight(Integer.valueOf(form.getHeight()));
//    }
//    if (StringUtils.isNotEmpty(form.getBlood())) {
//      minyi.setBlood(form.getBlood());
//    }
//    if (StringUtils.isNotEmpty(form.getBirthday())) {
//      DateTimeFormatter withoutZone = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//      LocalDateTime parsed = LocalDateTime.parse(form.getBirthday() + " 00:00:00", withoutZone);
//      Instant instant = parsed.toInstant(ZoneOffset.ofHours(9));
//      minyi.setBirthday(Date.from(instant));
//    }
//    if (StringUtils.isNotEmpty(form.getBirthplaceId())) {
//      minyi.setBirthplaceId(Integer.valueOf(form.getBirthplaceId()));
//    }
//    minyi.setUpdateAt(new Date());
//    return minyi;
//  }

  /**
   * for debug.
   */
  private void modelDump(Model model, String m) {
    logger.debug(" ");
    logger.debug("Model:{}", m);
    Map<String, Object> mm = model.asMap();
    for (Entry<String, Object> entry : mm.entrySet()) {
      logger.debug("key:{}, value:{}", entry.getKey(), entry.getValue().toString());
    }
  }

}
