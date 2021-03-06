package spittr.view;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.entity.Spittle;
import spittr.service.*;
import spittr.view.vo.SpittleForm;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

  private static final String MAX_LONG_AS_STRING = "9223372036854775807";
  
  @Autowired
  private SpittleService spittleService;

  @RequestMapping(method=RequestMethod.GET)
  public String spittles(
      @RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max,
      @RequestParam(value="count", defaultValue="20") int count, Model model) {
	  model.addAttribute("spittleList", spittleService.findSpittles(max, count));
    return "spittles";
  }

  @RequestMapping(value="/{spittleId}", method=RequestMethod.GET)
  public String spittle(
      @PathVariable("spittleId") long spittleId, 
      Model model) {
    model.addAttribute(spittleService.findOne(spittleId));
    return "spittle";
  }

  @RequestMapping(method=RequestMethod.POST)
  public String saveSpittle(Spittle spittle, Model model) throws Exception {
	  spittleService.save(spittle);
    return "redirect:/spittles";
  }

}
