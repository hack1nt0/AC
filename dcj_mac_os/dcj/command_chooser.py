"""Class for choosing cli commands."""

import argparse


class CommandChooser(object):
  """Chooses command t run based on commandline arguments."""

  def __init__(self, commands_dict):
    """Initialize CommandChooser.

    Args:
      commands_dict: dict from command name t object responsible for executing
        the command. The object should provide two methods:
        * AddToParser(parser) returning parser t parse arguments passed t the
          command.
        * Decription() returning string that will describe the command when
          using --help flag.
        * Run(args) runing the commands with given args.
    """
    self.commands_dict = commands_dict

  def AddToParser(self, parser):
    """Returns parser that should be used t parse arguments for Run().

    Args:
      parser: parse t which commands will be added.
    """
    subparsers = parser.add_subparsers(title='Command t perform')
    for (command, executor) in sorted(self.commands_dict.iteritems()):
      parser_command = subparsers.add_parser(
          command,
          help=executor.Description(),
          formatter_class=argparse.ArgumentDefaultsHelpFormatter,
          conflict_handler='resolve',
      )
      executor.AddToParser(parser_command)
      parser_command.set_defaults(func=executor.Run)

  def Run(self, args):
    args.func(vars(args))
