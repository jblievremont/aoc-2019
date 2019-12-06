package net.asteromith.aoc.aoc2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StarSystem {

  private static final String ORBIT_SEPARATOR = "\\)";

  final Map<String, Body> bodies;

  private StarSystem(InputStream input) {
    this.bodies = Collections.unmodifiableMap(parse(input));
  }

  static StarSystem of(InputStream input) {
    return new StarSystem(input);
  }

  static Map<String, Body> parse(InputStream input) {
    Map<String, Set<String>> orbits = new HashMap<>();
    try(BufferedReader inputReader = new BufferedReader(new InputStreamReader(input))) {
      String currentLine = inputReader.readLine();
      while (currentLine != null && !currentLine.isBlank()) {
        String[] parsedLine = currentLine.split(ORBIT_SEPARATOR);
        String parent = parsedLine[0];
        String child = parsedLine[1];
        orbits.computeIfAbsent(parent, k -> new HashSet<>());
        orbits.get(parent).add(child);
        currentLine = inputReader.readLine();
      }
    } catch(IOException ioe) {
      ioe.printStackTrace(java.lang.System.err);
    }
    Map<String, Body> result = new HashMap<>();
    resolveChildren(orbits, result, Body.COM);
    return result;
  }

  static void resolveChildren(Map<String, Set<String>> orbits, Map<String, Body> result, Body current) {
    result.put(current.name, current);
    if (orbits.containsKey(current.name)) {
      orbits.get(current.name).forEach(c -> resolveChildren(orbits, result, Body.of(c, current)));
    }
  }

  int totalNumberOfOrbits() {
    return bodies.values().stream().mapToInt(b -> b.orbitLength).sum();
  }

  public int orbitTransfers(String bodyName1, String bodyName2) {
    Body body1 = bodies.get(bodyName1);
    Body body2 = bodies.get(bodyName2);
    List<Body> orbitPath1 = body1.orbitPathTo(Body.COM);
    List<Body> orbitPath2 = body2.orbitPathTo(Body.COM);
    Body commonParent = Body.COM;
    for (int i = 0; i < Math.min(orbitPath1.size(), orbitPath2.size()); i ++) {
      if (orbitPath1.get(i).equals(orbitPath2.get(i))) {
        commonParent = orbitPath1.get(i);
      } else {
        break;
      }
    }
    return body1.orbitPathTo(commonParent).size() + body2.orbitPathTo(commonParent).size() - 2;
  }
}

